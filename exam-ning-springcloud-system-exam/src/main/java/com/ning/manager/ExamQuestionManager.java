package com.ning.manager;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.dao.ExamQuestionDao;
import com.ning.entity.ExamQuestion;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ExamQuestionManager {

    @Resource
    ExamQuestionDao examQuestionDao;

    /**
     * 分页查询考题列表
     *
     * @param type
     * @param keyword
     * @param pNum
     * @param pSize
     * @return
     */
    public IPage<ExamQuestion> selectExamQuestionPage(String type, String keyword, Integer pNum, Integer pSize) {
        // 分页对象
        IPage<ExamQuestion> iPage = new Page<>(pNum, pSize);

        // 查询对象
        LambdaQueryWrapper<ExamQuestion> wrapper = new QueryWrapper<ExamQuestion>().lambda();
        wrapper.eq(ExamQuestion::getIsDelete, 0);
        if (StrUtil.isNotEmpty(type)) {
            wrapper.eq(ExamQuestion::getType, type);
        }
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.like(ExamQuestion::getStem, keyword);
        }
        wrapper.orderByDesc(ExamQuestion::getCreateTime);

        return examQuestionDao.selectPage(iPage, wrapper);
    }

    /**
     * 添加考题
     *
     * @param examQuestion
     * @return
     */
    public Integer add(ExamQuestion examQuestion) {
        return examQuestionDao.insert(examQuestion);
    }

    /**
     * 修改考题
     *
     * @param examQuestion
     * @return
     */
    public Integer update(ExamQuestion examQuestion) {
        return examQuestionDao.updateById(examQuestion);
    }

    /**
     * 获取考题
     *
     * @param id
     * @return
     */
    public ExamQuestion get(Integer id) {
        return examQuestionDao.selectById(id);
    }

    /**
     * 批量查询题目
     *
     * @param ids
     * @return
     */
    public List<ExamQuestion> selectBatchIds(List<Integer> ids) {
        return examQuestionDao.selectBatchIds(ids);
    }

    /**
     * 批量修改题目使用数
     *
     * @param ids
     * @return
     */
    public Integer updateUsedNumBatchIds(List<Integer> ids) {
        return examQuestionDao.updateUsedNumBatchIds(ids);
    }

}
