package com.ning.dao;

import com.ning.entity.ExamQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 考题表 Mapper 接口
 * </p>
 *
 * @author ningning
 * @since 2021-06-04
 */
public interface ExamQuestionDao extends BaseMapper<ExamQuestion> {

    /**
     * 批量修改题目使用数
     *
     * @param ids
     * @return
     */
    Integer updateUsedNumBatchIds(@Param("ids") List<Integer> ids);

}
