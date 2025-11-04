package com.zxy.rental.controller;

import com.zxy.rental.entity.User;
import com.zxy.rental.service.IUserService;
import com.zxy.rental.utils.Result;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author student_zxy
 * @since 2025-10-31
 */
@RestController
@RequestMapping("/rental/user")
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping
    public Result<List<User>> list(){
        return Result.success(userService.list());
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<User> login(String username, String password){
        User user = userService.selectByUsername(username);
        if(user == null){
            return Result.fail("用户不存在");
        }
        if(!user.getPassword().equals(password)){
            return Result.fail("密码错误");
        }
        return Result.success(user);
    }
}

