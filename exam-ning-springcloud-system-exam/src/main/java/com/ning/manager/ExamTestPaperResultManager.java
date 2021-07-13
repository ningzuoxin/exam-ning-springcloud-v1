package com.ning.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.common.enums.ExamTestPaperResultStatusEnum;
import com.ning.dao.ExamTestPaperResultDao;
import com.ning.entity.ExamTestPaperResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class ExamTestPaperResultManager {

    @Resource
    ExamTestPaperResultDao examTestPaperResultDao;

    public Integer insert(ExamTestPaperResult examTestPaperResult) {
        return examTestPaperResultDao.insert(examTestPaperResult);
    }

    public ExamTestPaperResult selectById(Integer id) {
        return examTestPaperResultDao.selectById(id);
    }

    public Integer updateById(ExamTestPaperResult examTestPaperResult) {
        return examTestPaperResultDao.updateById(examTestPaperResult);
    }

    /**
     * 查询用户的考试结果次数
     *
     * @param testPaperIds
     * @param userId
     * @return
     */
    public List<Map<String, Object>> selectExamResultTimes(List<Integer> testPaperIds, Integer userId) {
        return examTestPaperResultDao.selectExamResultTimes(testPaperIds, userId);
    }

    /**
     * 查询考试结果列表
     *
     * @param id
     * @param userId
     * @return
     */
    public List<ExamTestPaperResult> list(Integer id, Integer userId) {
        // 查询对象
        LambdaQueryWrapper<ExamTestPaperResult> wrapper = new QueryWrapper<ExamTestPaperResult>().lambda();
        wrapper.eq(ExamTestPaperResult::getTestpaperId, id);
        wrapper.eq(ExamTestPaperResult::getUserId, userId);
        wrapper.orderByDesc(ExamTestPaperResult::getUpdateTime);
        return examTestPaperResultDao.selectList(wrapper);
    }

    /**
     * 分页查询待批阅列表
     *
     * @param pNum
     * @param pSize
     * @return
     */
    public IPage<ExamTestPaperResult> selectPage(Integer pNum, Integer pSize) {
        // 分页对象
        IPage<ExamTestPaperResult> iPage = new Page<>(pNum, pSize);

        // 查询对象
        LambdaQueryWrapper<ExamTestPaperResult> wrapper = new QueryWrapper<ExamTestPaperResult>().lambda();
        wrapper.eq(ExamTestPaperResult::getStatus, ExamTestPaperResultStatusEnum.REVIEWING.getType());
        wrapper.orderByDesc(ExamTestPaperResult::getUpdateTime);
        return examTestPaperResultDao.selectPage(iPage, wrapper);
    }

}
