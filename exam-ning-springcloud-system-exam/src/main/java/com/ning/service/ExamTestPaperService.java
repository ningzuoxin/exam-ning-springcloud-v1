package com.ning.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.common.model.ExamTestPaperModel;
import com.ning.entity.ExamTestPaper;
import com.ning.entity.ExamTestPaperItem;
import com.ning.manager.ExamTestPaperItemManager;
import com.ning.manager.ExamTestPaperManager;
import com.ning.model.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExamTestPaperService {

    @Resource
    ExamTestPaperManager examTestPaperManager;
    @Resource
    ExamTestPaperItemManager examTestPaperItemManager;

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

}
