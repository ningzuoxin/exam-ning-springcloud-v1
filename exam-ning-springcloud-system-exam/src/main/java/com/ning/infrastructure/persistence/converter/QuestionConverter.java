package com.ning.infrastructure.persistence.converter;

import com.ning.domain.entity.Question;
import com.ning.infrastructure.persistence.model.QuestionDO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 试题实体、DO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-11-21 14:00
 */
@Mapper
public interface QuestionConverter {

    QuestionConverter INSTANCE = Mappers.getMapper(QuestionConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", source = "id.value")
    @Mapping(target = "correctAnswer", expression = "java(cn.hutool.json.JSONUtil.toJsonStr(entity.getCorrectAnswer()))")
    @Mapping(target = "options", expression = "java(cn.hutool.json.JSONUtil.toJsonStr(entity.getOptions()))")
    QuestionDO toDO(Question entity);

    @Mapping(target = "id", expression = "java(new QuestionId(dataObject.getUid()))")
    @Mapping(target = "correctAnswer", expression = "java(cn.hutool.json.JSONUtil.toList(dataObject.getCorrectAnswer(), String.class))")
    @Mapping(target = "options", expression = "java(cn.hutool.json.JSONUtil.toList(dataObject.getOptions(), QuestionOption.class))")
    Question toEntity(QuestionDO dataObject);

    List<Question> toEntityList(List<QuestionDO> dataObjectList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDO(@MappingTarget QuestionDO dbDataObject, QuestionDO modifiedDataObject);

}
