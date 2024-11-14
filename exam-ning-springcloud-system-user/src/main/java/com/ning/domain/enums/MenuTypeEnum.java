package com.ning.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型枚举
 *
 * @author zuoxin.ning
 * @since 2024-11-14 08:00
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    /**
     * 目录
     */
    Catalog(1),
    /**
     * 菜单
     */
    Menu(2),
    /**
     * 按钮
     */
    Button(3),
    ;

    private final int value;

}
