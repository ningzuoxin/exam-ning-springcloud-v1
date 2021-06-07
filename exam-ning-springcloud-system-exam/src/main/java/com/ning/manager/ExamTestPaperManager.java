package com.ning.manager;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.common.model.ExamTestPaperModel;
import com.ning.dao.ExamTestPaperDao;
import com.ning.dao.ExamTestPaperResultDao;
import com.ning.entity.ExamTestPaper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@Component
public class ExamTestPaperManager {

    @Resource
    ExamTestPaperDao examTestPaperDao;
    @Resource
    ExamTestPaperResultDao examTestPaperResultDao;

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
     * @param userId
     * @return
     */
    public IPage<ExamTestPaperModel> listExam(String type, Integer pNum, Integer pSize, Integer userId) {
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
        IPage<ExamTestPaper> examTestPaperIPage = examTestPaperDao.selectPage(iPage, wrapper);
        List<ExamTestPaper> records = examTestPaperIPage.getRecords();

        List<Map<String, Object>> maps = examTestPaperResultDao.selectExamResultTimes(records.stream().map(t -> t.getId()).collect(Collectors.toList()), userId);

        List<ExamTestPaperModel> list = new ArrayList<>();
        for (ExamTestPaper record : records) {
            ExamTestPaperModel examTestPaperModel = new ExamTestPaperModel();
            BeanUtil.copyProperties(record, examTestPaperModel);
            examTestPaperModel.setResultTimes(0);
            
            for (Map<String, Object> map : maps) {
                int id = Convert.toInt(map.get("id"));
                int times = Convert.toInt(map.get("times"));
                if (id == record.getId()) {
                    examTestPaperModel.setResultTimes(times);
                }
            }
            list.add(examTestPaperModel);
        }

        // 封装数据
        Page<ExamTestPaperModel> page = new Page<>();
        BeanUtil.copyProperties(iPage, page);
        page.setRecords(list);

        return page;
    }

    public Integer updateById(ExamTestPaper examTestPaper) {
        return examTestPaperDao.updateById(examTestPaper);
    }

}
