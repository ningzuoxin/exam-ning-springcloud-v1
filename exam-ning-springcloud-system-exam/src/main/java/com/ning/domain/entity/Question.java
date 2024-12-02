package com.ning.domain.entity;

import com.ning.domain.types.QuestionId;
import com.ning.domain.types.QuestionOption;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 试题实体
 *
 * @author zuoxin.ning
 * @since 2024-11-21 08:00
 */
@Getter
@Setter
@ToString
public class Question {

    // 试题 ID
    private QuestionId id;
    // 题型
    private String type;
    // 题目内容
    private String content;
    // 正确答案
    private List<String> correctAnswer;
    // 解析
    private String explanation;
    // 选项
    private List<QuestionOption> options;

    public Question(QuestionId id, String type, String content, List<String> correctAnswer, String explanation, List<QuestionOption> options) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
        this.options = options;
    }

}
