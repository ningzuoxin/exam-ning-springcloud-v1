package com.ning.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 试卷结果详情 DTO
 *
 * @author zuoxin.ning
 * @since 2024-11-28 10:00
 */
@Getter
@Setter
public class PaperResultDetailDTO {

    // 试卷
    private PaperDTO paper;
    // 试卷结果
    private PaperResultDTO paperResult;
    // 试题列表
    private List<QuestionDTO> questionList;

}
