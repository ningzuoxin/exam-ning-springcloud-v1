package com.ning.application.assembler;

import com.ning.application.dto.PaperDTO;
import com.ning.domain.entity.Paper;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.interfaces.request.AddPaperRequest;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 试卷实体、DTO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-10-27 11:00
 */
@Mapper
public interface PaperAssembler {

    PaperAssembler INSTANCE = Mappers.getMapper(PaperAssembler.class);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "copyPaperId", source = "copyPaperId.value")
    PaperDTO toDTO(Paper entity);

    List<PaperDTO> toDTOList(List<Paper> entityList);

    PageWrapper<PaperDTO> toDTOPageList(PageWrapper<Paper> pageEntityList);

    PaperDTO toDTO(AddPaperRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "copyPaperId", expression = "java(new PaperId(dto.getCopyPaperId()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Paper entity, PaperDTO dto);

}
