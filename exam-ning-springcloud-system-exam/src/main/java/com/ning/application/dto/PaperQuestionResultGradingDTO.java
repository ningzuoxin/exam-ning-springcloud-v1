package com.ning.application.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 试卷试题结果评分 DTO
 *
 * @author zuoxin.ning
 * @since 2024-11-28 14:00
 */
@Getter
@Setter
public class PaperQuestionResultGradingDTO {

    // 试卷试题结果 ID
    private Long paperQuestionResultId;
    // 分数
    private Float score;

}
