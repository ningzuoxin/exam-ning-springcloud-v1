package com.ning.domain.repository;

import com.ning.domain.entity.PaperQuestionResult;
import com.ning.domain.types.PaperQuestionResultId;

import java.util.List;

/**
 * 试卷试题结果仓储
 *
 * @author zuoxin.ning
 * @since 2024-11-28 14:30
 */
public interface PaperQuestionResultRepository {

    /**
     * 查询试卷试题结果列表
     *
     * @param idList 试卷试题结果 ID 列表
     * @return 试卷试题结果列表
     */
    List<PaperQuestionResult> find(List<PaperQuestionResultId> idList);

    /**
     * 保存试卷试题结果
     *
     * @param paperQuestionResult 试卷试题结果
     * @return 试卷试题结果
     */
    PaperQuestionResult save(PaperQuestionResult paperQuestionResult);

}
