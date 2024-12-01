package com.ning.domain.check;

import com.ning.domain.enums.PaperQuestionResultStatusEnum;
import com.ning.domain.types.AnswerCheckResult;

import java.util.Objects;

/**
 * 单选题批阅答案策略接口
 *
 * @author zuoxin.ning
 * @since 2024-11-30 22:30
 */
public class SingleChoiceAnswerCheckStrategy implements AnswerCheckStrategy {

    /**
     * 批阅答案
     *
     * @param correctAnswer 正确答案
     * @param answer        用户答案
     * @param score         试题总分
     * @return 批阅答案结果
     */
    @Override
    public AnswerCheckResult check(String correctAnswer, String answer, Float score) {
        if (Objects.equals(correctAnswer, answer)) {
            return new AnswerCheckResult(PaperQuestionResultStatusEnum.CORRECT, score);
        }
        return new AnswerCheckResult(PaperQuestionResultStatusEnum.INCORRECT, 0f);
    }

}
