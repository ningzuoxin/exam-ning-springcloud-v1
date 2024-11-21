package com.ning.domain.repository;

import com.ning.domain.entity.Question;
import com.ning.domain.types.QuestionId;
import com.ning.infrastructure.common.model.PageWrapper;

import java.util.Optional;

/**
 * 试题仓储
 *
 * @author zuoxin.ning
 * @since 2024-11-21 14:00
 */
public interface QuestionRepository {

    /**
     * 查询试题
     *
     * @param id 试题 ID
     * @return 试题
     */
    Optional<Question> find(QuestionId id);

    /**
     * 保存试题
     *
     * @param question 试题
     * @return 试题
     */
    Question save(Question question);

    /**
     * 分页查询试题
     *
     * @param type     类型
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 试题列表
     */
    PageWrapper<Question> findByPage(String type, String keyword, Integer pageNum, Integer pageSize);

    /**
     * 删除试题
     *
     * @param id 试题 ID
     * @return 是否操作成功
     */
    boolean remove(QuestionId id);

}
