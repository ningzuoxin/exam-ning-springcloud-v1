package com.ning.domain.repository;

import com.ning.domain.entity.PaperResult;
import com.ning.domain.types.PaperId;
import com.ning.domain.types.PaperResultId;
import com.ning.infrastructure.common.model.PageWrapper;

import java.util.List;
import java.util.Optional;

/**
 * 试卷结果仓储
 *
 * @author zuoxin.ning
 * @since 2024-11-27 15:30
 */
public interface PaperResultRepository {

    /**
     * 查询试卷结果
     *
     * @param id 试卷结果 ID
     * @return 试卷结果
     */
    Optional<PaperResult> find(PaperResultId id);

    /**
     * 保存试卷结果
     *
     * @param paperResult 试卷结果
     * @return 试卷结果
     */
    PaperResult save(PaperResult paperResult);

    /**
     * 分页查询试卷结果列表
     *
     * @param status   试卷结果状态
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 试卷结果列表
     */
    PageWrapper<PaperResult> findByPage(Integer status, Integer pageNum, Integer pageSize);

    /**
     * 根据用户 ID 查询试卷结果列表
     *
     * @param userUid 用户 ID
     * @param paperId 试卷 ID
     * @return 试卷结果列表
     */
    List<PaperResult> findByUserId(Long userUid, PaperId paperId);

}
