package com.ning.infrastructure.persistence.repository.impl;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ning.domain.entity.PaperQuestion;
import com.ning.domain.repository.PaperQuestionRepository;
import com.ning.domain.types.PaperQuestionId;
import com.ning.infrastructure.persistence.converter.PaperQuestionConverter;
import com.ning.infrastructure.persistence.dao.PaperQuestionDao;
import com.ning.infrastructure.persistence.model.PaperQuestionDO;
import com.ning.infrastructure.utils.SnowFlake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 试卷试题仓储实现
 *
 * @author zuoxin.ning
 * @since 2024-11-28 14:30
 */
@Repository
@RequiredArgsConstructor
public class PaperQuestionRepositoryImpl implements PaperQuestionRepository {

    private final PaperQuestionDao paperQuestionDao;
    private final PaperQuestionConverter paperQuestionConverter = PaperQuestionConverter.INSTANCE;

    /**
     * 查询试卷试题列表
     *
     * @param idList 试卷试题 ID 列表
     * @return 试卷试题列表
     */
    @Override
    public List<PaperQuestion> find(List<PaperQuestionId> idList) {
        Optional<PaperQuestionId> paperQuestionIdOpt = idList.stream().filter(Objects::nonNull).findFirst();
        if (!paperQuestionIdOpt.isPresent()) {
            return ListUtil.empty();
        }

        Optional<PaperQuestionDO> paperQuestionDOOpt = this.findByUid(paperQuestionIdOpt.get().getValue());
        if (!paperQuestionDOOpt.isPresent()) {
            return ListUtil.empty();
        }

        List<Long> uidList = idList.stream().filter(Objects::nonNull).map(PaperQuestionId::getValue).collect(Collectors.toList());
        List<PaperQuestionDO> paperQuestionDOList = this.findByPaperUid(paperQuestionDOOpt.get().getPaperUid());
        paperQuestionDOList = paperQuestionDOList.stream().filter(t -> uidList.contains(t.getUid())).collect(Collectors.toList());
        return paperQuestionConverter.toEntityList(paperQuestionDOList);
    }

    /**
     * 保存试卷试题
     *
     * @param paperQuestion 试卷
     * @return 试卷试题
     */
    @Override
    public PaperQuestion save(PaperQuestion paperQuestion) {
        PaperQuestionDO paperQuestionDO = paperQuestionConverter.toDO(paperQuestion);
        if (Objects.isNull(paperQuestionDO.getUid())) {
            paperQuestionDO.setUid(SnowFlake.ID.nextId());
            paperQuestionDao.insert(paperQuestionDO);
            return paperQuestionConverter.toEntity(paperQuestionDO);
        } else {
            Optional<PaperQuestionDO> paperQuestionDOOpt = this.findByUid(paperQuestion.getId().getValue());
            if (!paperQuestionDOOpt.isPresent()) {
                throw new IllegalArgumentException("PaperQuestion not exits, id: " + paperQuestion.getId().getValue());
            }

            PaperQuestionDO dbPaperQuestionDO = paperQuestionDOOpt.get();
            paperQuestionConverter.updateDO(dbPaperQuestionDO, paperQuestionDO);
            paperQuestionDao.updateById(dbPaperQuestionDO);
            return paperQuestionConverter.toEntity(dbPaperQuestionDO);
        }
    }

    private List<PaperQuestionDO> findByPaperUid(Long paperUid) {
        QueryWrapper<PaperQuestionDO> wrapper = new QueryWrapper<>();
        wrapper.eq("paper_uid", paperUid);
        wrapper.eq("is_deleted", 0);
        return paperQuestionDao.selectList(wrapper);
    }

    private Optional<PaperQuestionDO> findByUid(Long uid) {
        QueryWrapper<PaperQuestionDO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.eq("is_deleted", 0);
        return Optional.ofNullable(paperQuestionDao.selectOne(wrapper));
    }

}
