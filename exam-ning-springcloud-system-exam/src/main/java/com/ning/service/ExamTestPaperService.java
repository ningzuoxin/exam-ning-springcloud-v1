package com.ning.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.common.enums.ExamQuestionTypeEnum;
import com.ning.common.model.ExamTestPaperModel;
import com.ning.entity.ExamQuestion;
import com.ning.entity.ExamTestPaper;
import com.ning.entity.ExamTestPaperItem;
import com.ning.entity.ExamTestPaperItemResult;
import com.ning.manager.*;
import com.ning.model.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
     * @return
     */
    public Result listExam(String type, Integer pNum, Integer pSize) {
        return Result.ok(examTestPaperManager.listExam(type, pNum, pSize));
    }

    /**
     * 交卷
     *
     * @param examTestPaperModel
     * @return
     */
    public Result submit(ExamTestPaperModel examTestPaperModel) {
        Integer result = examTestPaperResultManager.insert(examTestPaperModel.getExamTestPaperResult());
        List<ExamTestPaperItemResult> examTestPaperItemResults = examTestPaperModel.getExamTestPaperItemResults();
        for (ExamTestPaperItemResult examTestPaperItemResult : examTestPaperItemResults) {
            examTestPaperItemResultManager.insert(examTestPaperItemResult);
        }
        return Result.ok();
    }
}
