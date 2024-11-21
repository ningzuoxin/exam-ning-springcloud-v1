package com.ning.application.dto;

import com.ning.domain.types.QuestionId;
import lombok.Getter;
import lombok.Setter;

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
    private QuestionId id;
    // 题型
    private String type;
    // 题目内容
    private String content;
    // 正确答案
    private String correctAnswer;
    // 解析
    private String explanation;
    // 选项
    private String options;

}
