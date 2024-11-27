package com.ning.infrastructure.persistence.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.domain.entity.PaperResult;
import com.ning.domain.repository.PaperResultRepository;
import com.ning.domain.types.PaperId;
import com.ning.domain.types.PaperResultId;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.persistence.converter.PaperResultConverter;
import com.ning.infrastructure.persistence.dao.PaperResultDao;
import com.ning.infrastructure.persistence.model.PaperResultDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 试卷结果仓储实现
 *
 * @author zuoxin.ning
 * @since 2024-11-27 17:00
 */
@Repository
@RequiredArgsConstructor
public class PaperResultRepositoryImpl implements PaperResultRepository {

    private final PaperResultDao paperResultDao;
    private final PaperResultConverter paperResultConverter = PaperResultConverter.INSTANCE;

    /**
     * 查询试卷结果
     *
     * @param id 试卷结果 ID
     * @return 试卷结果
     */
    @Override
    public Optional<PaperResult> find(PaperResultId id) {
        Optional<PaperResultDO> paperResultDOOpt = this.findByUid(id.getValue());
        if (!paperResultDOOpt.isPresent()) {
            return Optional.empty();
        }

        PaperResult paperResult = paperResultConverter.toEntity(paperResultDOOpt.get());
        return Optional.of(paperResult);
    }

    /**
     * 保存试卷结果
     *
     * @param paperResult 试卷结果
     * @return 试卷结果
     */
    @Override
    public PaperResult save(PaperResult paperResult) {
        return null;
    }

    /**
     * 分页查询试卷结果列表
     *
     * @param status   试卷结果状态
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 试卷结果列表
     */
    @Override
    public PageWrapper<PaperResult> findByPage(Integer status, Integer pageNum, Integer pageSize) {
        // 分页对象
        IPage<PaperResultDO> iPage = new Page<>(pageNum, pageSize);

        // 查询对象
        LambdaQueryWrapper<PaperResultDO> wrapper = new QueryWrapper<PaperResultDO>().lambda();
        wrapper.eq(PaperResultDO::getIsDeleted, 0);
        if (Objects.nonNull(status)) {
            wrapper.eq(PaperResultDO::getStatus, status);
        }
        wrapper.orderByDesc(PaperResultDO::getUpdateTime);

        IPage<PaperResultDO> paperResultDOIPage = paperResultDao.selectPage(iPage, wrapper);
        return PageWrapper.<PaperResult>builder()
                .total(paperResultDOIPage.getTotal())
                .pageNum(pageNum)
                .pageSize(pageSize)
                .data(paperResultConverter.toEntityList(paperResultDOIPage.getRecords()))
                .build();
    }

    /**
     * 根据用户 ID 查询试卷结果列表
     *
     * @param userUid 用户 ID
     * @param paperId 试卷 ID
     * @return 试卷结果列表
     */
    @Override
    public List<PaperResult> findByUserId(Long userUid, PaperId paperId) {
        // 查询对象
        LambdaQueryWrapper<PaperResultDO> wrapper = new QueryWrapper<PaperResultDO>().lambda();
        wrapper.eq(PaperResultDO::getIsDeleted, 0);
        wrapper.eq(PaperResultDO::getUserUid, userUid);
        if (Objects.nonNull(paperId) && Objects.nonNull(paperId.getValue())) {
            wrapper.eq(PaperResultDO::getPaperUid, paperId.getValue());
        }
        wrapper.orderByDesc(PaperResultDO::getUpdateTime);

        List<PaperResultDO> paperResultDOList = paperResultDao.selectList(wrapper);
        return paperResultConverter.toEntityList(paperResultDOList);
    }

    private Optional<PaperResultDO> findByUid(Long uid) {
        QueryWrapper<PaperResultDO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.eq("is_deleted", 0);
        return Optional.ofNullable(paperResultDao.selectOne(wrapper));
    }

}
