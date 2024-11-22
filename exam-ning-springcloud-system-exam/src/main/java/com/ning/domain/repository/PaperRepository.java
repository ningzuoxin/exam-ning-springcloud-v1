package com.ning.domain.repository;

import com.ning.domain.entity.Paper;
import com.ning.domain.types.PaperId;
import com.ning.infrastructure.common.model.PageWrapper;

import java.util.Optional;

/**
 * 试卷仓储
 *
 * @author zuoxin.ning
 * @since 2024-11-22 17:00
 */
public interface PaperRepository {

    /**
     * 查询试卷
     *
     * @param id 试卷 ID
     * @return 试卷
     */
    Optional<Paper> find(PaperId id);

    /**
     * 分页查询试卷列表
     *
     * @param type     试卷类型
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 试卷列表
     */
    PageWrapper<Paper> findByPage(Integer type, String keyword, Integer pageNum, Integer pageSize);

    Paper save(Paper paper);

}
