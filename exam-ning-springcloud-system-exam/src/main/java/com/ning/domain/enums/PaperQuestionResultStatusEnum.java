package com.ning.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 试卷试题结果状态枚举
 *
 * @author zuoxin.ning
 * @since 2024-11-29 08:30
 */
@Getter
@AllArgsConstructor
public enum PaperQuestionResultStatusEnum {

    /**
     * 待批阅
     */
    PENDING(0, "待批阅"),
    /**
     * 正确
     */
    CORRECT(1, "正确"),
    /**
     * 部分正确
     */
    PARTIALLY_CORRECT(2, "部分正确"),
    /**
     * 错误
     */
    INCORRECT(3, "错误"),
    ;

    private final int value;
    private final String comment;

}
