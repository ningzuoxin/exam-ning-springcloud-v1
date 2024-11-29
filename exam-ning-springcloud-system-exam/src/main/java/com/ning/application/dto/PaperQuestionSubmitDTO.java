package com.ning.application.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 试卷试题提交 DTO
 *
 * @author zuoxin.ning
 * @since 2024-11-28 14:00
 */
@Getter
@Setter
public class PaperQuestionSubmitDTO {

    // 试卷试题 ID
    private Long paperQuestionId;
    // 答题结果
    private String answer;

}
