package com.zxy.rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author student_zxy
 * @since 2025-10-31
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("busi_maintain")
@ApiModel(value = "Maintain对象", description = "")
public class Maintain implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("保养id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("车辆id")
    private Integer autoId;

    @ApiModelProperty("车牌号码")
    private String autoNum;

    @ApiModelProperty("维保时间")
    private LocalDateTime maintainTime;

    @ApiModelProperty("维保地点")
    private String location;

    @ApiModelProperty("维保项目")
    private String item;

    @ApiModelProperty("维保费用")
    private Integer cost;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除")
    private Boolean deleted;
}
