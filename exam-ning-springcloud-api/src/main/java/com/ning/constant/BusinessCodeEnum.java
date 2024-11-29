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

    /**
     * 角色模块
     */
    ROLE_NOT_EXISTS(2001, "角色不存在"),
    ROLE_KEY_EXISTS(2002, "角色代码已经存在"),

    /**
     * 菜单模块
     */
    MENU_NOT_EXISTS(3001, "菜单不存在"),

    /**
     * 试题模块
     */
    QUESTION_NOT_EXISTS(4001, "试题不存在"),

    /**
     * 试卷模块
     */
    PAPER_NOT_EXISTS(5001, "试卷不存在"),

    /**
     * 试卷结果模块
     */
    PAPER_RESULT_NOT_EXISTS(6001, "试卷结果不存在"),
    ;

    private final Integer code;
    private final String message;
}
