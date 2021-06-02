package com.ning.common.mark;

import com.ning.entity.ExamQuestion;
import com.ning.entity.ExamTestPaperItem;
import com.ning.entity.ExamTestPaperItemResult;

/**
 * 判单选题和判断题
 */
public class CheckChoiceQuestion implements Check {

    @Override
    public ExamTestPaperItemResult check(ExamTestPaperItemResult examTestPaperItemResult, ExamQuestion examQuestion, ExamTestPaperItem examTestPaperItem) {
        examTestPaperItemResult.setStatus("wrong");
        examTestPaperItemResult.setScore(0F);

        String userAnswer = examTestPaperItemResult.getAnswer();
        if (examQuestion.getAnswer().equals(userAnswer)) {
            examTestPaperItemResult.setStatus("right");
            examTestPaperItemResult.setScore(examTestPaperItem.getScore());
        }
        return examTestPaperItemResult;
    }

}
