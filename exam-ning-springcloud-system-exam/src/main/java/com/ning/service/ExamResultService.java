package com.ning.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ning.common.model.ExamTestPaperModel;
import com.ning.entity.*;
import com.ning.manager.*;
import com.ning.model.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamResultService {

    @Resource
    ExamTestPaperResultManager examTestPaperResultManager;
    @Resource
    ExamTestPaperManager examTestPaperManager;
    @Resource
    ExamTestPaperItemManager examTestPaperItemManager;
    @Resource
    ExamTestPaperItemResultManager examTestPaperItemResultManager;
    @Resource
    ExamQuestionManager examQuestionManager;

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

    /**
     * 查询考试结果详情
     *
     * @param id
     * @return
     */
    public Result detail(Integer id) {
        ExamTestPaperResult examTestPaperResult = examTestPaperResultManager.selectById(id);
        if (ObjectUtil.isEmpty(examTestPaperResult)) {
            return Result.fail("不存在的考试结果");
        }

        ExamTestPaperModel examTestPaperModel = new ExamTestPaperModel();
        ExamTestPaper examTestPaper = examTestPaperManager.selectById(examTestPaperResult.getTestpaperId());
        BeanUtil.copyProperties(examTestPaper, examTestPaperModel);
        examTestPaperModel.setExamTestPaperResult(examTestPaperResult);

        List<ExamTestPaperItem> examTestPaperItems = examTestPaperItemManager.listByTestPaperId(examTestPaperResult.getTestpaperId());
        examTestPaperModel.setExamTestPaperItems(examTestPaperItems);

        List<ExamQuestion> examQuestions = examQuestionManager.selectBatchIds(examTestPaperItems.stream().map(t -> t.getQuestionId()).collect(Collectors.toList()));
        examTestPaperModel.setExamQuestions(examQuestions);

        List<ExamTestPaperItemResult> examTestPaperItemResults = examTestPaperItemResultManager.selectByExamTestPaperItemResultId(examTestPaperResult.getId());
        examTestPaperModel.setExamTestPaperItemResults(examTestPaperItemResults);

        return Result.ok(examTestPaperModel);
    }
}
