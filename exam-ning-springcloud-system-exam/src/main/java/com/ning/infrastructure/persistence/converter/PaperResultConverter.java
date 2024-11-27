package com.ning.infrastructure.persistence.converter;

import com.ning.domain.entity.PaperResult;
import com.ning.infrastructure.persistence.model.PaperResultDO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 试卷结果实体、DO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-11-27 14:00
 */
@Mapper
public interface PaperResultConverter {

    PaperResultConverter INSTANCE = Mappers.getMapper(PaperResultConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", source = "id.value")
    PaperResultDO toDO(PaperResult entity);

    @Mapping(target = "id", expression = "java(new PaperId(dataObject.getUid()))")
    PaperResult toEntity(PaperResultDO dataObject);

    List<PaperResult> toEntityList(List<PaperResultDO> dataObjectList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDO(@MappingTarget PaperResultDO dbDataObject, PaperResultDO modifiedDataObject);

}
