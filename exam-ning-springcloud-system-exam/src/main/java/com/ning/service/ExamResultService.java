package com.ning.service;

import com.ning.manager.ExamTestPaperResultManager;
import com.ning.model.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ExamResultService {

    @Resource
    ExamTestPaperResultManager examTestPaperResultManager;

    /**
     * 查询考试结果列表
     *
     * @param id
     * @param userId
     * @return
     */
    public Result list(Integer id, Integer userId) {
        return Result.ok(examTestPaperResultManager.list(id, userId));
    }


}
