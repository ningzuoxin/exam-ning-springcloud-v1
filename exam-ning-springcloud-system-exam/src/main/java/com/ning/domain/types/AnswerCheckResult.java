package com.ning.domain.types;

import com.ning.domain.enums.PaperQuestionResultStatusEnum;
import lombok.Value;

/**
 * 答案批阅结果
 *
 * @author zuoxin.ning
 * @since 2024-11-30 22:00
 */
@Value
public class AnswerCheckResult {

    // 批阅结果状态
    PaperQuestionResultStatusEnum status;
    // 得分
    Float score;

    public AnswerCheckResult(PaperQuestionResultStatusEnum status, Float score) {
        this.status = status;
        this.score = score;
    }

}
