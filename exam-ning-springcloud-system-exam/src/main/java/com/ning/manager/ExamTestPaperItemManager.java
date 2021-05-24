package com.ning.manager;

import com.ning.dao.ExamTestPaperItemDao;
import com.ning.entity.ExamTestPaperItem;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ExamTestPaperItemManager {

    @Resource
    ExamTestPaperItemDao examTestPaperItemDao;

    public Integer add(ExamTestPaperItem examTestPaperItem) {
        return examTestPaperItemDao.insert(examTestPaperItem);
    }

}
