package com.ning.domain.check;

import com.ning.domain.types.AnswerCheckResult;

/**
 * 批阅答案策略接口
 *
 * @author zuoxin.ning
 * @since 2024-11-30 22:30
 */
public interface AnswerCheckStrategy {

    /**
     * 批阅答案
     *
     * @param correctAnswer 正确答案
     * @param answer        用户答案
     * @param score         试题总分
     * @return 批阅答案结果
     */
    AnswerCheckResult check(String correctAnswer, String answer, Float score);

}
