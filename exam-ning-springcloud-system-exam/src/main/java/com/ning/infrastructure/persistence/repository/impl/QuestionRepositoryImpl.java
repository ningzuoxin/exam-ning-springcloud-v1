package com.ning.infrastructure.persistence.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.domain.entity.Question;
import com.ning.domain.repository.QuestionRepository;
import com.ning.domain.types.QuestionId;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.persistence.converter.QuestionConverter;
import com.ning.infrastructure.persistence.dao.QuestionDao;
import com.ning.infrastructure.persistence.model.QuestionDO;
import com.ning.infrastructure.utils.SnowFlake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

/**
 * 试题仓储实现
 *
 * @author zuoxin.ning
 * @since 2024-11-21 14:00
 */
@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository {

    private final QuestionDao questionDao;
    private final QuestionConverter questionConverter = QuestionConverter.INSTANCE;

    /**
     * 查询试题
     *
     * @param id 试题 ID
     * @return 试题
     */
    @Override
    public Optional<Question> find(QuestionId id) {
        Optional<QuestionDO> questionDOOpt = this.findByUid(id.getValue());
        if (!questionDOOpt.isPresent()) {
            return Optional.empty();
        }

        Question question = questionConverter.toEntity(questionDOOpt.get());
        return Optional.of(question);
    }

    /**
     * 保存试题
     *
     * @param question 试题
     * @return 试题
     */
    @Override
    public Question save(Question question) {
        QuestionDO questionDO = questionConverter.toDO(question);
        if (Objects.isNull(questionDO.getUid())) {
            questionDO.setUid(SnowFlake.ID.nextId());
            questionDao.insert(questionDO);
            return questionConverter.toEntity(questionDO);
        } else {
            Optional<QuestionDO> questionDOOpt = this.findByUid(question.getId().getValue());
            if (!questionDOOpt.isPresent()) {
                throw new IllegalArgumentException("Question not exits, id: " + question.getId().getValue());
            }

            QuestionDO dbQuestionDO = questionDOOpt.get();
            questionConverter.updateDO(dbQuestionDO, questionDO);
            questionDao.updateById(dbQuestionDO);
            return questionConverter.toEntity(dbQuestionDO);
        }
    }

    /**
     * 分页查询试题
     *
     * @param type     类型
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 试题列表
     */
    @Override
    public PageWrapper<Question> findByPage(String type, String keyword, Integer pageNum, Integer pageSize) {
        // 分页对象
        IPage<QuestionDO> iPage = new Page<>(pageNum, pageSize);

        // 查询对象
        LambdaQueryWrapper<QuestionDO> wrapper = new QueryWrapper<QuestionDO>().lambda();
        wrapper.eq(QuestionDO::getIsDeleted, 0);
        if (StrUtil.isNotEmpty(type)) {
            wrapper.eq(QuestionDO::getType, type);
        }
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.like(QuestionDO::getContent, keyword);
        }
        wrapper.orderByDesc(QuestionDO::getCreateTime);

        IPage<QuestionDO> questionDOIPage = questionDao.selectPage(iPage, wrapper);
        return PageWrapper.<Question>builder()
                .total(questionDOIPage.getTotal())
                .pageNum(pageNum)
                .pageSize(pageSize)
                .data(questionConverter.toEntityList(questionDOIPage.getRecords()))
                .build();
    }

    /**
     * 删除试题
     *
     * @param id 试题 ID
     * @return 是否操作成功
     */
    @Override
    public boolean remove(QuestionId id) {
        Optional<QuestionDO> questionDOOpt = this.findByUid(id.getValue());
        if (!questionDOOpt.isPresent()) {
            return true;
        }

        QuestionDO questionDO = questionDOOpt.get();
        questionDO.setIsDeleted(1);
        questionDao.updateById(questionDO);
        return true;
    }

    private Optional<QuestionDO> findByUid(Long uid) {
        QueryWrapper<QuestionDO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.eq("is_deleted", 0);
        return Optional.ofNullable(questionDao.selectOne(wrapper));
    }

}
