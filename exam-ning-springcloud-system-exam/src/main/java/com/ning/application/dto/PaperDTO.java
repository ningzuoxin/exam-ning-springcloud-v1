package com.ning.application.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 试卷 DTO
 *
 * @author zuoxin.ning
 * @since 2024-11-27 11:00
 */
@Getter
@Setter
public class PaperDTO {

    // 试卷 ID
    private Long id;
    // 试卷类型
    private Integer type;
    // 试卷标题
    private String title;
    // 考试时间限制
    private Integer timeLimit;
    // 考试次数限制
    private Integer frequencyLimit;
    // 总分
    private Integer totalScore;
    // 及格分
    private Integer passedScore;
    // 试题数量
    private Integer questionCount;
    // 复制试卷ID
    private Long copyPaperId;
    // 试卷状态
    private Integer status;

}
