package com.ning.infrastructure.persistence.converter;

import com.ning.domain.entity.Paper;
import com.ning.infrastructure.persistence.model.PaperDO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 试卷实体、DO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-11-27 14:00
 */
@Mapper
public interface PaperConverter {

    PaperConverter INSTANCE = Mappers.getMapper(PaperConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", source = "id.value")
    PaperDO toDO(Paper entity);

    @Mapping(target = "id", expression = "java(new PaperId(dataObject.getUid()))")
    Paper toEntity(PaperDO dataObject);

    List<Paper> toEntityList(List<PaperDO> dataObjectList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDO(@MappingTarget PaperDO dbDataObject, PaperDO modifiedDataObject);

}
