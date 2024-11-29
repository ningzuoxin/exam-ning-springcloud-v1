package com.ning.domain.entity;

import com.ning.domain.enums.PaperStatusEnum;
import com.ning.domain.types.PaperId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 试卷实体
 *
 * @author zuoxin.ning
 * @since 2024-11-22 17:00
 */
@Getter
@Setter
@ToString
public class Paper {

    // 试卷 ID
    private PaperId id;
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
    private PaperId copyPaperId;
    // 试卷状态
    private Integer status;

    public Paper(PaperId id, Integer type, String title, Integer timeLimit, Integer frequencyLimit, Integer totalScore, Integer passedScore, Integer questionCount, PaperId copyPaperId) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.timeLimit = timeLimit;
        this.frequencyLimit = frequencyLimit;
        this.totalScore = totalScore;
        this.passedScore = passedScore;
        this.questionCount = questionCount;
        this.copyPaperId = copyPaperId;
        this.status = PaperStatusEnum.NORMAL.getValue();
    }

}