package com.ning.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private Long id;
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
