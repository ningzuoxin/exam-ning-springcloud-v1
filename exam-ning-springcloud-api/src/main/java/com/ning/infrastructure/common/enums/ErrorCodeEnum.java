package com.ning.infrastructure.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Error code enumeration
 *
 * @author zuoxin.ning
 * @since 2024-10-25 14:00
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    /**
     * General module
     */
    SUCCESS("0", "Operation succeeded"),
    FAILED("-1", "Operation failed"),
    VALIDATE_FAILED("400", "Parameter validation failed"),
    UNAUTHORIZED("401", "Not logged in or token has expired"),
    FORBIDDEN("403", "No relevant permissions"),
    PARAMETER_BLANK("410", "Parameter is blank"),

    /**
     * User module
     */
    USER_NOT_EXISTS("1001", "User does not exist"),
    USER_USERNAME_EXISTS("1002", "Username already exists"),

    /**
     * Role module
     */
    ROLE_NOT_EXISTS("2001", "Role does not exist"),
    ROLE_KEY_EXISTS("2002", "Role code already exists"),

    /**
     * Menu module
     */
    MENU_NOT_EXISTS("3001", "Menu does not exist"),

    /**
     * Question module
     */
    QUESTION_NOT_EXISTS("4001", "Question does not exist"),

    /**
     * Paper module
     */
    PAPER_NOT_EXISTS("5001", "Paper does not exist"),

    /**
     * Paper result module
     */
    PAPER_RESULT_NOT_EXISTS("6001", "Result does not exist"),

    ;

    private final String code;
    private final String message;

}
