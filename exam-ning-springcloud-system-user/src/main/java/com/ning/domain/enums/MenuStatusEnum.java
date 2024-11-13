package com.ning.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单状态枚举
 *
 * @author zuoxin.ning
 * @since 2024-11-13 22:00
 */
@Getter
@AllArgsConstructor
public enum MenuStatusEnum {

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
