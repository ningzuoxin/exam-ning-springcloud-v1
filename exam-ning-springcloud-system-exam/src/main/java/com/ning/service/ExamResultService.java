package com.ning.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ning.common.enums.ExamTestPaperResultStatusEnum;
import com.ning.common.model.ExamTestPaperModel;
import com.ning.common.model.MarkScoreModel;
import com.ning.entity.*;
import com.ning.manager.*;
import com.ning.model.Result;
import com.ning.utils.SecurityUtils;
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

    /**
     * 查询待批阅试卷列表
     *
     * @param pNum
     * @param pSize
     * @return
     */
    public Result selectPage(Integer pNum, Integer pSize) {
        return Result.ok(examTestPaperResultManager.selectPage(pNum, pSize));
    }

    /**
     * 打分
     *
     * @param markScoreModel
     * @return
     */
    public Result doMark(MarkScoreModel markScoreModel) {
        ExamTestPaperResult examTestPaperResult = examTestPaperResultManager.selectById(markScoreModel.getResultId());
        if (ObjectUtil.isEmpty(examTestPaperResult)) {
            return Result.fail("不存在的考试结果");
        }

        Float total = 0f;
        Integer right = 0;
        for (MarkScoreModel.MarkScoreItemModel item : markScoreModel.getMarkScoreItems()) {
            ExamTestPaperItemResult examTestPaperItemResult = examTestPaperItemResultManager.selectById(item.getId());
            ExamTestPaperItem examTestPaperItem = examTestPaperItemManager.selectById(examTestPaperItemResult.getTestpaperItemId());

            if (item.getScore() >= examTestPaperItem.getScore()) {
                examTestPaperItemResult.setStatus("right");
                right++;
            } else if (item.getScore() == 0) {
                examTestPaperItemResult.setStatus("wrong");
            } else {
                examTestPaperItemResult.setStatus("partRight");
            }
            examTestPaperItemResult.setScore(item.getScore());
            total += item.getScore();
            examTestPaperItemResultManager.updateById(examTestPaperItemResult);
        }

        examTestPaperResult.setStatus(ExamTestPaperResultStatusEnum.FINISHED.getType());
        examTestPaperResult.setObjectiveScore(total);
        examTestPaperResult.setScore(examTestPaperResult.getSubjectiveScore() + total);
        examTestPaperResult.setRightItemCount(examTestPaperResult.getRightItemCount() + right);
        examTestPaperResult.setCheckUserId(SecurityUtils.getLoginUser().getUserId().intValue());
        examTestPaperResult.setCheckedTime((int) DateUtil.currentSeconds());
        examTestPaperResult.setUpdateTime((int) DateUtil.currentSeconds());
        examTestPaperResultManager.updateById(examTestPaperResult);

        return Result.ok();
    }
}
