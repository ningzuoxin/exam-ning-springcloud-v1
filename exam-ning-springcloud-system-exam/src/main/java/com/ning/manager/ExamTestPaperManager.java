package com.ning.manager;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.dao.ExamTestPaperDao;
import com.ning.entity.ExamTestPaper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@SuppressWarnings("ALL")
@Component
public class ExamTestPaperManager {

    @Resource
    ExamTestPaperDao examTestPaperDao;

    public Integer add(ExamTestPaper examTestPaper) {
        return examTestPaperDao.insert(examTestPaper);
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
    public IPage<ExamTestPaper> selectExamTestPaperPage(String type, String keyword, Integer pNum, Integer pSize) {
        // 分页对象
        IPage<ExamTestPaper> iPage = new Page<>(pNum, pSize);

        // 查询对象
        LambdaQueryWrapper<ExamTestPaper> wrapper = new QueryWrapper<ExamTestPaper>().lambda();
        wrapper.eq(ExamTestPaper::getIsDelete, 0);
        if (StrUtil.isNotEmpty(type)) {
            wrapper.eq(ExamTestPaper::getType, type);
        }
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.like(ExamTestPaper::getName, keyword);
        }
        wrapper.orderByDesc(ExamTestPaper::getCreateTime);

        return examTestPaperDao.selectPage(iPage, wrapper);
    }

    public ExamTestPaper selectById(Integer id) {
        return examTestPaperDao.selectById(id);
    }

    /**
     * 查询考试列表
     *
     * @param type
     * @param pNum
     * @param pSize
     * @return
     */
    public IPage<ExamTestPaper> listExam(String type, Integer pNum, Integer pSize) {
        // 分页对象
        IPage<ExamTestPaper> iPage = new Page<>(pNum, pSize);

        // 查询对象
        LambdaQueryWrapper<ExamTestPaper> wrapper = new QueryWrapper<ExamTestPaper>().lambda();
        wrapper.eq(ExamTestPaper::getIsDelete, 0);
        wrapper.eq(ExamTestPaper::getIsUsed, 1);
        if (StrUtil.isNotEmpty(type)) {
            wrapper.eq(ExamTestPaper::getType, type);
        }
        wrapper.orderByDesc(ExamTestPaper::getCreateTime);

        return examTestPaperDao.selectPage(iPage, wrapper);
    }

}
