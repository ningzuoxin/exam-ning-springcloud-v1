package com.ning.domain.check;

import com.ning.domain.enums.QuestionTypeEnum;
import com.ning.domain.types.AnswerCheckResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 答案批阅处理器
 *
 * @author zuoxin.ning
 * @since 2024-12-01 10:00
 */
public class AnswerCheckHandler {

    // 不同题型的处理器集合
    private static final Map<QuestionTypeEnum, AnswerCheckStrategy> STRATEGY_MAP = new HashMap<>();

    static {
        STRATEGY_MAP.put(QuestionTypeEnum.SINGLE_CHOICE, new SingleChoiceAnswerCheckStrategy());
        STRATEGY_MAP.put(QuestionTypeEnum.MULTIPLE_CHOICE, new MultipleChoiceAnswerCheckStrategy());
        STRATEGY_MAP.put(QuestionTypeEnum.TRUE_FALSE, new SingleChoiceAnswerCheckStrategy());
        STRATEGY_MAP.put(QuestionTypeEnum.FILL_IN_BLANK, new FillInBlankAnswerCheckStrategy());
    }

    /**
     * 批阅答案
     *
     * @param questionType  试题类型
     * @param correctAnswer 正确答案
     * @param answer        用户答案
     * @param score         试题总分
     * @return 批阅答案结果
     */
    public static AnswerCheckResult check(QuestionTypeEnum questionType, String correctAnswer, String answer, Float score) {
        AnswerCheckStrategy answerCheckStrategy = STRATEGY_MAP.get(questionType);
        if (Objects.isNull(answerCheckStrategy)) {
            throw new IllegalArgumentException("unsupported question type");
        }
        return answerCheckStrategy.check(correctAnswer, answer, score);
    }

}
