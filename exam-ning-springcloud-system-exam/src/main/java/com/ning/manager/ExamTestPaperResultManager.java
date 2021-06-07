package com.ning.manager;

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

}
