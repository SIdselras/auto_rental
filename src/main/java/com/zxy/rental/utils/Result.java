package com.zxy.rental.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @auther student_zxy
 * @date 2025/10/31
 * @project auto_rental
 */
@Data
@Accessors(chain = true)
public class Result<T> {
    private Integer code;
    private String message;
    private Boolean success;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<T>().setCode(ResultCode.SUCCESS)
                .setData(data)
                .setSuccess(true)
                .setMessage("操作成功");
    }

    public static <T> Result<T> success() {
        return new Result<T>()
                .setCode(ResultCode.SUCCESS)
                .setSuccess(true)
                .setMessage("操作成功");
    }

    public static <T> Result<T> fail(String message) {
        return new Result<T>().setCode(ResultCode.ERROR)
                .setSuccess(false)
                .setMessage(message);
    }

    public static <T> Result<T> fail() {
        return new Result<T>().setCode(ResultCode.ERROR)
                .setSuccess(false)
                .setMessage("操作失败");
    }
}
