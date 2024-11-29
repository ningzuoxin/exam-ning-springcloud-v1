package com.ning.domain.repository;

import com.ning.domain.entity.PaperQuestion;
import com.ning.domain.types.PaperQuestionId;

import java.util.List;

/**
 * 试卷试题仓储
 *
 * @author zuoxin.ning
 * @since 2024-11-28 14:30
 */
public interface PaperQuestionRepository {

    /**
     * 查询试卷试题列表
     *
     * @param idList 试卷试题 ID 列表
     * @return 试卷试题列表
     */
    List<PaperQuestion> find(List<PaperQuestionId> idList);

    /**
     * 保存试卷试题
     *
     * @param paperQuestion 试卷
     * @return 试卷试题
     */
    PaperQuestion save(PaperQuestion paperQuestion);

}
