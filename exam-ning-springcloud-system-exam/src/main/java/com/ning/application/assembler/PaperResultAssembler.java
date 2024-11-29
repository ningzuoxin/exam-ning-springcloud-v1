package com.ning.application.assembler;

import com.ning.application.dto.PaperQuestionResultGradingDTO;
import com.ning.application.dto.PaperResultDTO;
import com.ning.domain.entity.PaperResult;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.interfaces.request.AddPaperRequest;
import com.ning.interfaces.request.PaperResultGradingRequest;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 试卷结果实体、DTO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-11-28 08:00
 */
@Mapper
public interface PaperResultAssembler {

    PaperResultAssembler INSTANCE = Mappers.getMapper(PaperResultAssembler.class);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "paperId", source = "paperId.value")
    PaperResultDTO toDTO(PaperResult entity);

    List<PaperResultDTO> toDTOList(List<PaperResult> entityList);

    PageWrapper<PaperResultDTO> toDTOPageList(PageWrapper<PaperResult> pageEntityList);

    PaperResultDTO toDTO(AddPaperRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paperId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget PaperResult entity, PaperResultDTO dto);

    List<PaperQuestionResultGradingDTO> toGradingDTOList(List<PaperResultGradingRequest.PaperQuestionResultGradingRequest> requestList);

}
