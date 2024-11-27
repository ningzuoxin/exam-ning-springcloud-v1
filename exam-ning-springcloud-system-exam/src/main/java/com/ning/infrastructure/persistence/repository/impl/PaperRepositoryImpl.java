package com.ning.infrastructure.persistence.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.domain.entity.Paper;
import com.ning.domain.repository.PaperRepository;
import com.ning.domain.types.PaperId;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.persistence.converter.PaperConverter;
import com.ning.infrastructure.persistence.dao.PaperDao;
import com.ning.infrastructure.persistence.model.PaperDO;
import com.ning.infrastructure.utils.SnowFlake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

/**
 * 试卷仓储实现
 *
 * @author zuoxin.ning
 * @since 2024-11-27 11:00
 */
@Repository
@RequiredArgsConstructor
public class PaperRepositoryImpl implements PaperRepository {

    private final PaperDao paperDao;
    private final PaperConverter paperConverter = PaperConverter.INSTANCE;

    /**
     * 查询试卷
     *
     * @param id 试卷 ID
     * @return 试卷
     */
    @Override
    public Optional<Paper> find(PaperId id) {
        Optional<PaperDO> paperDOOpt = this.findByUid(id.getValue());
        if (!paperDOOpt.isPresent()) {
            return Optional.empty();
        }

        Paper paper = paperConverter.toEntity(paperDOOpt.get());
        return Optional.of(paper);
    }

    /**
     * 分页查询试卷列表
     *
     * @param type     试卷类型
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 试卷列表
     */
    @Override
    public PageWrapper<Paper> findByPage(Integer type, String keyword, Integer pageNum, Integer pageSize) {
        // 分页对象
        IPage<PaperDO> iPage = new Page<>(pageNum, pageSize);

        // 查询对象
        LambdaQueryWrapper<PaperDO> wrapper = new QueryWrapper<PaperDO>().lambda();
        wrapper.eq(PaperDO::getIsDeleted, 0);
        if (Objects.nonNull(type)) {
            wrapper.eq(PaperDO::getType, type);
        }
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.like(PaperDO::getTitle, keyword);
        }
        wrapper.orderByDesc(PaperDO::getCreateTime);

        IPage<PaperDO> paperDOIPage = paperDao.selectPage(iPage, wrapper);
        return PageWrapper.<Paper>builder()
                .total(paperDOIPage.getTotal())
                .pageNum(pageNum)
                .pageSize(pageSize)
                .data(paperConverter.toEntityList(paperDOIPage.getRecords()))
                .build();
    }

    /**
     * 保存试卷
     *
     * @param paper 试卷
     * @return 试卷
     */
    @Override
    public Paper save(Paper paper) {
        PaperDO paperDO = paperConverter.toDO(paper);
        if (Objects.isNull(paperDO.getUid())) {
            paperDO.setUid(SnowFlake.ID.nextId());
            paperDao.insert(paperDO);
            return paperConverter.toEntity(paperDO);
        } else {
            Optional<PaperDO> paperDOOpt = this.findByUid(paper.getId().getValue());
            if (!paperDOOpt.isPresent()) {
                throw new IllegalArgumentException("Paper not exits, id: " + paper.getId().getValue());
            }

            PaperDO dbPaperDO = paperDOOpt.get();
            paperConverter.updateDO(dbPaperDO, paperDO);
            paperDao.updateById(dbPaperDO);
            return paperConverter.toEntity(dbPaperDO);
        }
    }

    private Optional<PaperDO> findByUid(Long uid) {
        QueryWrapper<PaperDO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.eq("is_deleted", 0);
        return Optional.ofNullable(paperDao.selectOne(wrapper));
    }

}
