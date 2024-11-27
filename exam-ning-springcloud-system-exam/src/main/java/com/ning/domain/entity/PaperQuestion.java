package com.ning.domain.entity;

import com.ning.domain.types.PaperId;
import com.ning.domain.types.PaperQuestionId;
import com.ning.domain.types.QuestionId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 试卷试题实体
 *
 * @author zuoxin.ning
 * @since 2024-11-27 15:30
 */
@Getter
@Setter
@ToString
public class PaperQuestion {

    // 试卷试题 ID
    private PaperQuestionId id;
    // 试卷 ID
    private PaperId paperId;
    // 试题 ID
    private QuestionId questionId;
    // 答对得分
    private Float rightScore;
    // 漏选得分
    private Float missScore;
    // 顺序
    private Integer sortNum;

}
