package com.ning.common.mark;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.ning.entity.ExamQuestion;
import com.ning.entity.ExamTestPaperItem;
import com.ning.entity.ExamTestPaperItemResult;

/**
 * 判多选题
 */
public class CheckChoiceMultiQuestion implements Check {

    @Override
    public ExamTestPaperItemResult check(ExamTestPaperItemResult examTestPaperItemResult, ExamQuestion examQuestion, ExamTestPaperItem examTestPaperItem) {
        examTestPaperItemResult.setStatus("wrong");
        examTestPaperItemResult.setScore(0F);

        String userAnswer = examTestPaperItemResult.getAnswer();
        if (examQuestion.getAnswer().equals(userAnswer)) {
            examTestPaperItemResult.setStatus("right");
            examTestPaperItemResult.setScore(examTestPaperItem.getScore());
        } else if (isPartRight(examQuestion.getAnswer(), userAnswer)) {
            examTestPaperItemResult.setStatus("partRight");
            examTestPaperItemResult.setScore(examTestPaperItem.getScore() / 2);
        }
        return examTestPaperItemResult;
    }

    private Boolean isPartRight(String rightAnswer, String userAnswer) {
        boolean result = false;

        JSONArray rightAnswerArray = JSONUtil.parseArray(rightAnswer); // 正确答案
        JSONArray userAnswerArray = JSONUtil.parseArray(userAnswer); // 用户答案
        for (int i = 0; i < userAnswerArray.size(); i++) {
            if (rightAnswerArray.indexOf(userAnswerArray.get(i)) == -1) {
                result = false;
                break;
            } else {
                result = true;
            }
        }

        return result;
    }

}
