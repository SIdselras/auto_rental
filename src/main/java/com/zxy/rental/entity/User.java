package com.zxy.rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author student_zxy
 * @since 2025-10-31
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户账户")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("账户是否过期")
    private boolean isAccountNonExpired;

    @ApiModelProperty("账户是否被锁定")
    private boolean isAccountNonLocked;

    @ApiModelProperty("密码是否过期")
    private boolean isCredentialsNonExpired;

    @ApiModelProperty("账户是否可用")
    private boolean isEnabled;

    @ApiModelProperty("用户真实姓名")
    private String realname;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("所属部门id")
    private Integer deptId;

    @ApiModelProperty("所属部门名称")
    private String deptName;

    @ApiModelProperty("性别")
    private Boolean gender;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("是否管理员")
    private Boolean isAdmin;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除")
    private Boolean deleted;

    @TableField(exist = false)
    // 获取认证权限
    private Collection<? extends GrantedAuthority> authorities;

    @TableField(exist = false)
    private List<Permission> permissionList;
}
