package com.ning.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 试卷结果状态枚举
 *
 * @author zuoxin.ning
 * @since 2024-11-28 10:30
 */
@Getter
@AllArgsConstructor
public enum PaperResultStatusEnum {

    /**
     * 待阅卷
     */
    PENDING(0, "待阅卷"),
    /**
     * 阅卷中
     */
    IN_PROGRESS(1, "阅卷中"),
    /**
     * 暂停
     */
    PAUSED(2, "暂停"),
    /**
     * 阅卷结束
     */
    COMPLETED(3, "阅卷结束"),
    ;

    private final int value;
    private final String comment;

}
