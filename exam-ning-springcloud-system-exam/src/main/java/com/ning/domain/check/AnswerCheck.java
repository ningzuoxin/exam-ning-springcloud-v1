package com.ning.domain.check;

import com.ning.domain.types.AnswerCheckResult;

/**
 * 答案批阅接口
 *
 * @author zuoxin.ning
 * @since 2024-11-30 22:30
 */
public interface AnswerCheck {

    AnswerCheckResult check(String correctAnswer, String answer, Float score);

}
