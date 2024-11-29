package com.ning.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 试卷预览 DTO
 *
 * @author zuoxin.ning
 * @since 2024-11-27 11:00
 */
@Getter
@Setter
@Builder
public class PaperPreviewDTO {

    // 试卷
    private PaperDTO paper;
    // 试卷试题列表
    private List<PaperQuestionDTO> paperQuestionList;
    // 试题列表
    private List<QuestionDTO> questionList;

}
