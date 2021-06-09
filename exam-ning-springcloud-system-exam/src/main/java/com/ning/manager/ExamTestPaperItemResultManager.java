package com.ning.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ning.dao.ExamTestPaperItemResultDao;
import com.ning.entity.ExamTestPaperItemResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ExamTestPaperItemResultManager {

    @Resource
    ExamTestPaperItemResultDao examTestPaperItemResultDao;

    public Integer insert(ExamTestPaperItemResult examTestPaperItemResult) {
        return examTestPaperItemResultDao.insert(examTestPaperItemResult);
    }

    public List<ExamTestPaperItemResult> selectByExamTestPaperItemResultId(Integer testPaperResultId) {
        // 查询对象
        LambdaQueryWrapper<ExamTestPaperItemResult> wrapper = new QueryWrapper<ExamTestPaperItemResult>().lambda();
        wrapper.eq(ExamTestPaperItemResult::getTestpaperResultId, testPaperResultId);
        return examTestPaperItemResultDao.selectList(wrapper);
    }

    public Integer updateById(ExamTestPaperItemResult examTestPaperItemResult) {
        return examTestPaperItemResultDao.updateById(examTestPaperItemResult);
    }

    public ExamTestPaperItemResult selectById(Integer id) {
        return examTestPaperItemResultDao.selectById(id);
    }

}
