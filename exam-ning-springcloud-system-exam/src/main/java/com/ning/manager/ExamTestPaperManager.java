package com.ning.manager;

import com.ning.dao.ExamTestPaperDao;
import com.ning.entity.ExamTestPaper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ExamTestPaperManager {

    @Resource
    ExamTestPaperDao examTestPaperDao;

    public Integer add(ExamTestPaper examTestPaper) {
        return examTestPaperDao.insert(examTestPaper);
    }

}
