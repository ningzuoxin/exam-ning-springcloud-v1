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
    SUCCESS(0, "操作成功"),
    FAILED(-1, "操作失败"),
    VALIDATE_FAILED(400, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    PARAMETER_BLANK(410, "参数为空"),

    /**
     * 用户模块
     */
    USER_NOT_EXISTS(1001, "用户不存在"),
    USER_USERNAME_EXISTS(1002, "用户名已经存在"),
    ;

    private final Integer code;
    private final String message;
}
