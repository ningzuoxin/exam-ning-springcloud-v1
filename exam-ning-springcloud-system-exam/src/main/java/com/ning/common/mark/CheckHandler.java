package com.ning.common.mark;

import com.ning.common.enums.ExamQuestionTypeEnum;
import com.ning.entity.ExamQuestion;
import com.ning.entity.ExamTestPaperItem;
import com.ning.entity.ExamTestPaperItemResult;
import org.springframework.stereotype.Component;

@Component
public class CheckHandler {

    public ExamTestPaperItemResult check(ExamTestPaperItemResult examTestPaperItemResult, ExamQuestion examQuestion, ExamTestPaperItem examTestPaperItem) {
        if (ExamQuestionTypeEnum.CHOICE.getType().equals(examTestPaperItem.getQuestionType()) || ExamQuestionTypeEnum.TRUE_FALSE.getType().equals(examTestPaperItem.getQuestionType())) {
            Check check = new CheckChoiceQuestion();
            return check.check(examTestPaperItemResult, examQuestion, examTestPaperItem);
        }

        if (ExamQuestionTypeEnum.CHOICE_MULTI.getType().equals(examTestPaperItem.getQuestionType())) {
            Check check = new CheckChoiceMultiQuestion();
            return check.check(examTestPaperItemResult, examQuestion, examTestPaperItem);
        }

        if (ExamQuestionTypeEnum.FILL_BLANK.getType().equals(examTestPaperItem.getQuestionType())) {
            Check check = new CheckFillBlankQuestion();
            return check.check(examTestPaperItemResult, examQuestion, examTestPaperItem);
        }

        return null;
    }

}
