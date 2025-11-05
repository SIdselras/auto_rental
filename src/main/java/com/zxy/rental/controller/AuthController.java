package com.zxy.rental.controller;

import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTPayload;
import com.zxy.rental.entity.Permission;
import com.zxy.rental.security.CustomerAuthenticationException;
import com.zxy.rental.service.IUserService;
import com.zxy.rental.service.impl.UserServiceImpl;
import com.zxy.rental.utils.JwtUtils;
import com.zxy.rental.utils.RedisUtils;
import com.zxy.rental.utils.Result;
import com.zxy.rental.utils.RouteTreeUtils;
import com.zxy.rental.vo.RouteVO;
import com.zxy.rental.vo.TokenVO;
import com.zxy.rental.entity.User;
import com.zxy.rental.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @auther student_zxy
 * @date 2025/11/4
 * @project auto_rental
 */
@RestController
@RequestMapping("/rental/auth/")
@Api(tags = "token刷新")
public class AuthController {
    @Resource
    private RedisUtils redisUtils;
    @Autowired
    private IUserService userService;

    @PostMapping("/refreshToken")
    @ApiOperation("刷新token")
    public Result refreshToken(HttpServletRequest request){
        //获取token
        String token = request.getHeader("token");
        if(StrUtil.isEmpty(token)){
            token = request.getParameter("token");
        }
        //从SecurityContextHolder上下文中获取Authentication对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //从token中获取用户名
        String username = JwtUtils.parseToken(token).getClaim("username").toString();
        String newToken = "";
        if(StrUtil.equals(username, userDetails.getUsername())){
            Map<String, Object> map = new HashMap<>();
            map.put("username",userDetails.getUsername());
            newToken = JwtUtils.createToken(map);
        }else {
            throw new CustomerAuthenticationException("token数据异常");
        }
        //获取本次过期时间
        NumberWithFormat claim = (NumberWithFormat) JwtUtils.parseToken(newToken).getClaim(JWTPayload.EXPIRES_AT);
        DateTime dateTime = (DateTime) claim.convert(DateTime.class,claim);
        long expireTime = dateTime.getTime();

        //创建一个tokenvo的对象，将新生成的token和过期时间封装起来
        TokenVO tokenVO = new TokenVO();
        tokenVO.setToken(newToken).setExpireTime(expireTime);

        //清除原有token
        redisUtils.del("token:" + token);
        //将新的token存入Redis中
        long nowTime = DateTime.now().getTime();
        String newTokenKey = "token:" + newToken;
        redisUtils.set(newTokenKey, newToken, (expireTime - nowTime) / 1000);
        return Result.success(tokenVO).setMessage("刷新Token成功");
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/getInfo")
    @ApiOperation("前端获取用户信息")
    public Result getUserInfo(){
        // 从securityContextHolder上下文中获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Result.fail().setMessage("认证信息为空");
        }
        User user = (User) authentication.getPrincipal();
        // 查询用户角色名称
        /*List<String> list = userService.selectRoleName(user.getId());
        Object[] array = list.toArray(); */// 将角色名称列表转换为数组
        List<Permission> permissionList = user.getPermissionList();
        Object[] array = permissionList.stream().filter(Objects::nonNull)
                .map(Permission::getPermissionCode)
                .toArray();
        // 创建并填充用户信息视图对象
        UserInfoVO userInfoVo = new UserInfoVO(user.getId(), user.getUsername(),
                user.getAvatar(), user.getNickname(), array);
        return Result.success(userInfoVo).setMessage("获取用户信息成功");
    }

    @GetMapping("/menuList")
    @ApiOperation("前端获取菜单列表")
    public Result getMenuList(){
        //获取当前用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            return Result.fail("认证信息为空");
        }
        User user = (User) authentication.getPrincipal();
        List<Permission> permissionList = user.getPermissionList();
        //获取用户的菜单
        //将permission_type为2的按钮移除，不需要生成对应的菜单
        permissionList.removeIf(permission -> Objects.equals(permission.getPermissionType(), 2));

        List<RouteVO> routeVOList = RouteTreeUtils.buildRouteTree(permissionList, 0);
        return Result.success(routeVOList).setMessage("获取菜单列表成功");
    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response){
        String token=request.getHeader("token");
        if (StrUtil.isEmpty(token)){
            token=request.getParameter("token");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null){
            //用户一旦登出系统，则清除redis中的token
            redisUtils.del("token:"+token);
            SecurityContextLogoutHandler handler = new SecurityContextLogoutHandler();
            handler.logout(request,response,authentication);
            return Result.success().setMessage("登出成功");
        }
        return Result.fail().setMessage("登出失败");
    }
}
