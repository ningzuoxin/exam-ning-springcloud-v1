package com.ning.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色状态枚举
 *
 * @author zuoxin.ning
 * @since 2024-10-31 14:00
 */
@Getter
@AllArgsConstructor
public enum RoleStatusEnum {

    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 停用
     */
    DEACTIVATE(1),
    ;

    private final int value;

}
