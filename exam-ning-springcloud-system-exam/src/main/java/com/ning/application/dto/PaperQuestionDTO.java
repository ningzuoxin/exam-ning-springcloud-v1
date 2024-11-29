package com.ning.application.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 试卷试题 DTO
 *
 * @author zuoxin.ning
 * @since 2024-11-29 11:30
 */
@Getter
@Setter
public class PaperQuestionDTO {

    // 试卷试题 ID
    private Long id;
    // 试卷 ID
    private Long paperId;
    // 试题 ID
    private Long questionId;
    // 答对得分
    private Float rightScore;
    // 漏选得分
    private Float missScore;
    // 顺序
    private Integer sortNum;

}
