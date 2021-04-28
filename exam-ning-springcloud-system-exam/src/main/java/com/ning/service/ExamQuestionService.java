package com.ning.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.entity.ExamQuestion;
import com.ning.manager.ExamQuestionManager;
import com.ning.model.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ExamQuestionService {

    @Resource
    ExamQuestionManager examQuestionManager;

    /**
     * 分页查询考题列表
     *
     * @param type
     * @param keyword
     * @param pNum
     * @param pSize
     * @return
     */
    public Result<IPage<ExamQuestion>> selectExamQuestionPage(String type, String keyword, Integer pNum, Integer pSize) {
        return Result.ok(examQuestionManager.selectExamQuestionPage(type, keyword, pNum, pSize));
    }

}
