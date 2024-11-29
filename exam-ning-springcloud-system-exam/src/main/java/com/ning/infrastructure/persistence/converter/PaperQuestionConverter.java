package com.ning.infrastructure.persistence.converter;

import com.ning.domain.entity.PaperQuestion;
import com.ning.infrastructure.persistence.model.PaperQuestionDO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 试卷试题实体、DO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-11-28 14:00
 */
@Mapper
public interface PaperQuestionConverter {

    PaperQuestionConverter INSTANCE = Mappers.getMapper(PaperQuestionConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", source = "id.value")
    @Mapping(target = "paperUid", source = "paperId.value")
    @Mapping(target = "questionUid", source = "questionId.value")
    PaperQuestionDO toDO(PaperQuestion entity);

    @Mapping(target = "id", expression = "java(new PaperQuestionId(dataObject.getUid()))")
    @Mapping(target = "paperId", expression = "java(new PaperId(dataObject.getPaperUid()))")
    @Mapping(target = "questionId", expression = "java(new QuestionId(dataObject.getQuestionUid()))")
    PaperQuestion toEntity(PaperQuestionDO dataObject);

    List<PaperQuestion> toEntityList(List<PaperQuestionDO> dataObjectList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDO(@MappingTarget PaperQuestionDO dbDataObject, PaperQuestionDO modifiedDataObject);

}
