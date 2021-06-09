package com.ning.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ning.dao.ExamTestPaperItemDao;
import com.ning.entity.ExamTestPaperItem;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ExamTestPaperItemManager {

    @Resource
    ExamTestPaperItemDao examTestPaperItemDao;

    public Integer add(ExamTestPaperItem examTestPaperItem) {
        return examTestPaperItemDao.insert(examTestPaperItem);
    }

    /**
     * 根据试卷id查询试卷项目
     *
     * @param testPaperId
     * @return
     */
    public List<ExamTestPaperItem> listByTestPaperId(Integer testPaperId) {
        // 查询对象
        LambdaQueryWrapper<ExamTestPaperItem> wrapper = new QueryWrapper<ExamTestPaperItem>().lambda();
        wrapper.eq(ExamTestPaperItem::getTestPaperId, testPaperId);
        wrapper.orderByAsc(ExamTestPaperItem::getSeq);
        return examTestPaperItemDao.selectList(wrapper);
    }

    public ExamTestPaperItem selectById(Integer id) {
        return examTestPaperItemDao.selectById(id);
    }

}
