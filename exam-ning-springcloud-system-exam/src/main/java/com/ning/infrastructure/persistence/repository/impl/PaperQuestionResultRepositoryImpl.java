package com.ning.infrastructure.persistence.repository.impl;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ning.domain.entity.PaperQuestionResult;
import com.ning.domain.repository.PaperQuestionResultRepository;
import com.ning.domain.types.PaperQuestionResultId;
import com.ning.infrastructure.persistence.converter.PaperQuestionResultConverter;
import com.ning.infrastructure.persistence.dao.PaperQuestionResultDao;
import com.ning.infrastructure.persistence.model.PaperQuestionResultDO;
import com.ning.infrastructure.utils.SnowFlake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 试卷试题结果仓储实现
 *
 * @author zuoxin.ning
 * @since 2024-11-28 14:30
 */
@Repository
@RequiredArgsConstructor
public class PaperQuestionResultRepositoryImpl implements PaperQuestionResultRepository {

    private final PaperQuestionResultDao paperQuestionResultDao;
    private final PaperQuestionResultConverter paperQuestionResultConverter = PaperQuestionResultConverter.INSTANCE;

    /**
     * 查询试卷试题结果列表
     *
     * @param idList 试卷试题结果 ID 列表
     * @return 试卷试题结果列表
     */
    @Override
    public List<PaperQuestionResult> find(List<PaperQuestionResultId> idList) {
        Optional<PaperQuestionResultId> paperQuestionResultIdOpt = idList.stream().filter(Objects::nonNull).findFirst();
        if (!paperQuestionResultIdOpt.isPresent()) {
            return ListUtil.empty();
        }

        Optional<PaperQuestionResultDO> paperQuestionResultDOOpt = this.findByUid(paperQuestionResultIdOpt.get().getValue());
        if (!paperQuestionResultDOOpt.isPresent()) {
            return ListUtil.empty();
        }

        List<Long> uidList = idList.stream().filter(Objects::nonNull).map(PaperQuestionResultId::getValue).collect(Collectors.toList());
        List<PaperQuestionResultDO> paperQuestionResultDOList = this.findByPaperResultUid(paperQuestionResultDOOpt.get().getPaperResultUid());
        paperQuestionResultDOList = paperQuestionResultDOList.stream().filter(t -> uidList.contains(t.getUid())).collect(Collectors.toList());
        return paperQuestionResultConverter.toEntityList(paperQuestionResultDOList);
    }

    /**
     * 保存试卷试题结果
     *
     * @param paperQuestionResult 试卷试题结果
     * @return 试卷试题结果
     */
    @Override
    public PaperQuestionResult save(PaperQuestionResult paperQuestionResult) {
        PaperQuestionResultDO paperQuestionResultDO = paperQuestionResultConverter.toDO(paperQuestionResult);
        if (Objects.isNull(paperQuestionResultDO.getUid())) {
            paperQuestionResultDO.setUid(SnowFlake.ID.nextId());
            paperQuestionResultDao.insert(paperQuestionResultDO);
            return paperQuestionResultConverter.toEntity(paperQuestionResultDO);
        } else {
            Optional<PaperQuestionResultDO> paperQuestionResultDOOpt = this.findByUid(paperQuestionResult.getId().getValue());
            if (!paperQuestionResultDOOpt.isPresent()) {
                throw new IllegalArgumentException("PaperQuestionResult not exits, id: " + paperQuestionResult.getId().getValue());
            }

            PaperQuestionResultDO dbPaperQuestionResultDO = paperQuestionResultDOOpt.get();
            paperQuestionResultConverter.updateDO(dbPaperQuestionResultDO, paperQuestionResultDO);
            paperQuestionResultDao.updateById(dbPaperQuestionResultDO);
            return paperQuestionResultConverter.toEntity(dbPaperQuestionResultDO);
        }
    }

    private List<PaperQuestionResultDO> findByPaperResultUid(Long paperResultUid) {
        QueryWrapper<PaperQuestionResultDO> wrapper = new QueryWrapper<>();
        wrapper.eq("paper_result_uid", paperResultUid);
        wrapper.eq("is_deleted", 0);
        return paperQuestionResultDao.selectList(wrapper);
    }

    private Optional<PaperQuestionResultDO> findByUid(Long uid) {
        QueryWrapper<PaperQuestionResultDO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.eq("is_deleted", 0);
        return Optional.ofNullable(paperQuestionResultDao.selectOne(wrapper));
    }

}
