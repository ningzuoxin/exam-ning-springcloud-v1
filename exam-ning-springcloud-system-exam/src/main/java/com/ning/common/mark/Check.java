package com.ning.common.mark;

import com.ning.entity.ExamQuestion;
import com.ning.entity.ExamTestPaperItem;
import com.ning.entity.ExamTestPaperItemResult;

/**
 * 判题接口
 */
public interface Check {

    ExamTestPaperItemResult check(ExamTestPaperItemResult examTestPaperItemResult, ExamQuestion examQuestion, ExamTestPaperItem examTestPaperItem);

}
