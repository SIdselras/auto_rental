package com.zxy.rental.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @auther student_zxy
 * @date 2025/11/4
 * @project auto_rental
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RouteVO {
    private String path;
    private String component;
    private String name;
    private Boolean alwaysShow;

    private Meta meta;
    private List<RouteVO> children;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public class Meta{
        private String title;
        private String icon;
        private Object[] roles;
    }

}
