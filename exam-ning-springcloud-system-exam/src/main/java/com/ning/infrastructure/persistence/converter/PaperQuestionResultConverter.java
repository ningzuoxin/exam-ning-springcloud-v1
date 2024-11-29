package com.ning.infrastructure.persistence.converter;

import com.ning.domain.entity.PaperQuestionResult;
import com.ning.infrastructure.persistence.model.PaperQuestionResultDO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 试卷试题结果实体、DO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-11-28 14:00
 */
@Mapper
public interface PaperQuestionResultConverter {

    PaperQuestionResultConverter INSTANCE = Mappers.getMapper(PaperQuestionResultConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", source = "id.value")
    @Mapping(target = "paperResultUid", source = "paperResultId.value")
    @Mapping(target = "paperUid", source = "paperId.value")
    @Mapping(target = "paperQuestionUid", source = "paperQuestionId.value")
    @Mapping(target = "questionUid", source = "questionId.value")
    PaperQuestionResultDO toDO(PaperQuestionResult entity);

    @Mapping(target = "id", expression = "java(new PaperQuestionResultId(dataObject.getUid()))")
    @Mapping(target = "paperResultId", expression = "java(new PaperResultId(dataObject.getPaperResultUid()))")
    @Mapping(target = "paperId", expression = "java(new PaperId(dataObject.getPaperUid()))")
    @Mapping(target = "paperQuestionId", expression = "java(new PaperQuestionId(dataObject.getPaperQuestionUid()))")
    @Mapping(target = "questionId", expression = "java(new QuestionId(dataObject.getQuestionUid()))")
    PaperQuestionResult toEntity(PaperQuestionResultDO dataObject);

    List<PaperQuestionResult> toEntityList(List<PaperQuestionResultDO> dataObjectList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDO(@MappingTarget PaperQuestionResultDO dbDataObject, PaperQuestionResultDO modifiedDataObject);

}
