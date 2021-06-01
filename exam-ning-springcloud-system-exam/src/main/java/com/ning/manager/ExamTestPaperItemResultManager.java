package com.ning.manager;

import com.ning.dao.ExamTestPaperItemResultDao;
import com.ning.entity.ExamTestPaperItemResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ExamTestPaperItemResultManager {

    @Resource
    ExamTestPaperItemResultDao examTestPaperItemResultDao;

    public Integer insert(ExamTestPaperItemResult examTestPaperItemResult) {
        return examTestPaperItemResultDao.insert(examTestPaperItemResult);
    }

}
