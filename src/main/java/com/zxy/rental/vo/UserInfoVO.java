package com.zxy.rental.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @auther student_zxy
 * @date 2025/11/4
 * @project auto_rental
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserInfoVO {
    private Integer id;
    private String introduction;
    private String avatar;
    private String name;
    private Object[] roles;
}
