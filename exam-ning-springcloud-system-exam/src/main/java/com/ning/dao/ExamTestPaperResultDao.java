package com.ning.dao;

import com.ning.entity.ExamTestPaperResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 试卷结果表 Mapper 接口
 * </p>
 *
 * @author ningning
 * @since 2021-06-01
 */

public interface ExamTestPaperResultDao extends BaseMapper<ExamTestPaperResult> {

    /**
     * 查询用户的考试结果次数
     *
     * @param testPaperIds
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectExamResultTimes(@Param("testPaperIds") List<Integer> testPaperIds, @Param("userId") Integer userId);
}
