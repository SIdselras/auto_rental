package com.zxy.rental.security;


import com.zxy.rental.entity.Permission;
import com.zxy.rental.entity.User;
import com.zxy.rental.service.IPermissionService;
import com.zxy.rental.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @auther student_zxy
 * @date 2025/10/31
 * @project auto_rental
 */
@Component
public class CustomerUserDetailsService implements UserDetailsService {
    @Resource
    private IUserService userService;
    @Resource
    private IPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        //查询用户权限列表
        List<Permission> permissions = permissionService.selectPermissionByUserId(user.getId());
        user.setPermissionList(permissions);

        //通过stream流处理，将权限对象转换为权限字符串列表
        List<String> list = permissions.stream().filter(Objects::nonNull)
                .map(Permission::getPermissionCode)
                .filter(Objects::nonNull)
                .toList();
        String[] array = list.toArray(new String[list.size()]);
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(array);
        user.setAuthorities(authorityList);
        return user;
        //todo

    }
}
