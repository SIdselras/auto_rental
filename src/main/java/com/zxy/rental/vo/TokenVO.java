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
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TokenVO {
    private String token;
    private Long expireTime;
}
