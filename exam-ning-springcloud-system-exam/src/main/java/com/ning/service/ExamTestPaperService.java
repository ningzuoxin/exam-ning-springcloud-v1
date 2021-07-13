package com.ning.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.common.enums.ExamQuestionTypeEnum;
import com.ning.common.enums.ExamTestPaperResultStatusEnum;
import com.ning.common.event.MarkTestPaperStartEvent;
import com.ning.common.model.ExamTestPaperModel;
import com.ning.entity.*;
import com.ning.manager.*;
import com.ning.model.Result;
import com.ning.utils.SecurityUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@Service
public class ExamTestPaperService {

    @Resource
    ExamTestPaperManager examTestPaperManager;
    @Resource
    ExamTestPaperItemManager examTestPaperItemManager;
    @Resource
    ExamQuestionManager examQuestionManager;
    @Resource
    ExamTestPaperResultManager examTestPaperResultManager;
    @Resource
    ExamTestPaperItemResultManager examTestPaperItemResultManager;
    @Resource
    ApplicationContext applicationContext;

    /**
     * 添加试卷
     *
     * @param examTestPaperModel
     * @return
     */
    public Result add(ExamTestPaperModel examTestPaperModel) {
        List<ExamTestPaperItem> examTestPaperItems = examTestPaperModel.getExamTestPaperItems();
        if (ObjectUtil.isEmpty(examTestPaperItems)) {
            return Result.fail("试卷的试题不能为空");
        }

        Integer result = examTestPaperManager.add(examTestPaperModel);
        if (result != 1) {
            return Result.fail("添加试卷失败，请检查数据！");
        }

        for (ExamTestPaperItem examTestPaperItem : examTestPaperItems) {
            examTestPaperItem.setTestPaperId(examTestPaperModel.getId());
            examTestPaperItemManager.add(examTestPaperItem);
        }

        Integer updatedCount = examQuestionManager.updateUsedNumBatchIds(examTestPaperItems.stream().map(t -> t.getQuestionId()).collect(Collectors.toList()));

        return Result.ok(examTestPaperModel);
    }

    /**
     * 分页查询试卷列表
     *
     * @param type
     * @param keyword
     * @param pNum
     * @param pSize
     * @return
     */
    public Result<IPage<ExamTestPaper>> selectExamTestPaperPage(String type, String keyword, Integer pNum, Integer pSize) {
        return Result.ok(examTestPaperManager.selectExamTestPaperPage(type, keyword, pNum, pSize));
    }

    /**
     * 预览试卷
     *
     * @param id
     * @return
     */
    public Result<ExamTestPaperModel> preview(Integer id) {
        ExamTestPaperModel examTestPaperModel = new ExamTestPaperModel();
        ExamTestPaper examTestPaper = examTestPaperManager.selectById(id);
        BeanUtil.copyProperties(examTestPaper, examTestPaperModel);

        List<ExamTestPaperItem> examTestPaperItems = examTestPaperItemManager.listByTestPaperId(id);
        // 排序一下
        examTestPaperItems.sort((t1, t2) -> {
            int i1 = ExamQuestionTypeEnum.getIndex(t1.getQuestionType());
            int i2 = ExamQuestionTypeEnum.getIndex(t2.getQuestionType());
            return i1 - i2;
        });
        examTestPaperModel.setExamTestPaperItems(examTestPaperItems);

        List<ExamQuestion> examQuestions = examQuestionManager.selectBatchIds(examTestPaperItems.stream().map(m -> m.getQuestionId()).collect(Collectors.toList()));
        // 排序一下
        examQuestions.sort((t1, t2) -> {
            int i1 = ExamQuestionTypeEnum.getIndex(t1.getType());
            int i2 = ExamQuestionTypeEnum.getIndex(t2.getType());
            return i1 - i2;
        });
        examTestPaperModel.setExamQuestions(examQuestions);
        return Result.ok(examTestPaperModel);
    }

    /**
     * 查询考试列表
     *
     * @param type
     * @param pNum
     * @param pSize
     * @param userId
     * @return
     */
    public Result listExam(String type, Integer pNum, Integer pSize, Integer userId) {
        return Result.ok(examTestPaperManager.listExam(type, pNum, pSize, userId));
    }

    /**
     * 交卷
     *
     * @param examTestPaperModel
     * @return
     */
    public Result submit(ExamTestPaperModel examTestPaperModel) {
        int now = (int) DateUtil.currentSeconds();
        // 添加试卷结果
        ExamTestPaperResult examTestPaperResult = examTestPaperModel.getExamTestPaperResult();
        examTestPaperResult.setStatus(ExamTestPaperResultStatusEnum.DOING.getType());
        examTestPaperResult.setUpdateTime(now);
        examTestPaperResult.setUserId(SecurityUtils.getLoginUser().getUserId().intValue());
        Integer result = examTestPaperResultManager.insert(examTestPaperResult);
        if (result != 1) {
            return Result.fail("提交试卷失败，请检查数据！");
        }

        // 添加试卷项目结果
        List<ExamTestPaperItemResult> examTestPaperItemResults = examTestPaperModel.getExamTestPaperItemResults();
        for (ExamTestPaperItemResult examTestPaperItemResult : examTestPaperItemResults) {
            examTestPaperItemResult.setTestpaperResultId(examTestPaperResult.getId());
            examTestPaperItemResult.setUserId(SecurityUtils.getLoginUser().getUserId().intValue());
            examTestPaperItemResultManager.insert(examTestPaperItemResult);
        }

        // 发布阅卷开始事件
        applicationContext.publishEvent(new MarkTestPaperStartEvent(this, examTestPaperResult.getId()));
        return Result.ok();
    }

    /**
     * 发布试卷
     *
     * @param id
     * @return
     */
    public Result publish(Integer id) {
        ExamTestPaper examTestPaper = examTestPaperManager.selectById(id);
        if (ObjectUtil.isEmpty(examTestPaper)) {
            return Result.fail("不存在的试卷，请核实参数！");
        }

        examTestPaper.setIsUsed(1);
        examTestPaper.setUpdateTime((int) DateUtil.currentSeconds());
        examTestPaper.setUpdateUserId(SecurityUtils.getLoginUser().getUserId().intValue());
        Integer result = examTestPaperManager.updateById(examTestPaper);
        if (result != 1) {
            return Result.fail("试卷发布失败，请重新操作！");
        }

        return Result.ok();
    }

    /**
     * 删除试卷
     *
     * @param id
     * @return
     */
    public Result delete(Integer id) {
        ExamTestPaper examTestPaper = examTestPaperManager.selectById(id);
        if (ObjectUtil.isEmpty(examTestPaper)) {
            return Result.fail("不存在的试卷，请核实参数！");
        }

        examTestPaper.setIsDelete(1);
        examTestPaper.setUpdateTime((int) DateUtil.currentSeconds());
        examTestPaper.setUpdateUserId(SecurityUtils.getLoginUser().getUserId().intValue());
        Integer result = examTestPaperManager.updateById(examTestPaper);
        if (result != 1) {
            return Result.fail("试卷删除失败，请重新操作！");
        }

        return Result.ok();
    }
}
