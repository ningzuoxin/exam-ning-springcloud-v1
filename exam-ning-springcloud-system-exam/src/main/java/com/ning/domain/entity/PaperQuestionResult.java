package com.ning.domain.entity;

import com.ning.domain.types.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 试卷试题结果实体
 *
 * @author zuoxin.ning
 * @since 2024-11-27 15:30
 */
@Getter
@Setter
@ToString
public class PaperQuestionResult {

    // 试卷试题结果 ID
    private PaperQuestionResultId id;
    // 试卷结果 ID
    private PaperResultId paperResultId;
    // 试卷 ID
    private PaperId paperId;
    // 试卷试题ID
    private PaperQuestionId paperQuestionId;
    // 试题 ID
    private QuestionId questionId;
    // 答题者用户 ID
    private Long userUid;
    // 答题结果
    private String answer;
    // 结果状态，0：未答；1：正确；2：部分正确；3：错误；
    private Integer status;
    // 得分
    private Float score;
    // 教师点评
    private String comments;

}
