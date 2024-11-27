package com.ning.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 试卷状态枚举
 *
 * @author zuoxin.ning
 * @since 2024-11-27 14:00
 */
@Getter
@AllArgsConstructor
public enum PaperStatusEnum {

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
