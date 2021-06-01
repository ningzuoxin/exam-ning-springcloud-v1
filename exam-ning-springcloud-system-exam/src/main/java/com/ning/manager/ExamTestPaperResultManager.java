package com.ning.manager;

import com.ning.dao.ExamTestPaperResultDao;
import com.ning.entity.ExamTestPaperResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ExamTestPaperResultManager {

    @Resource
    ExamTestPaperResultDao examTestPaperResultDao;

    public Integer insert(ExamTestPaperResult examTestPaperResult) {
        return examTestPaperResultDao.insert(examTestPaperResult);
    }

}
