package com.ning.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 试题 DTO
 *
 * @author zuoxin.ning
 * @since 2024-11-21 15:00
 */
@Getter
@Setter
public class QuestionDTO {

    // 试题 ID
    private Long id;
    // 题型
    private String type;
    // 题目内容
    private String content;
    // 解析
    private String explanation;
    // 正确答案
    private List<String> correctAnswer;
    // 选项
    private List<QuestionOptionDTO> options;

}
