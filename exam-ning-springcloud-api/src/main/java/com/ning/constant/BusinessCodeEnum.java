package com.ning.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务码枚举
 *
 * @author zuoxin.ning
 * @since 2024-10-25 14:00
 */
@Getter
@AllArgsConstructor
public enum BusinessCodeEnum {

    /**
     * 通用模块
     */
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    VALIDATE_FAILED(404, "参数检验失败"),
    PARAMETER_BLANK(410, "参数为空"),

    /**
     * 用户模块
     */
    USER_USERNAME_EXISTS(400, "用户名已经存在"),
    ;

    private final Integer code;
    private final String message;
}
