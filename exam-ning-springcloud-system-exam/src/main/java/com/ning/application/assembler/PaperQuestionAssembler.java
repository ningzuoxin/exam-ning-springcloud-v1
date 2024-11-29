package com.ning.application.assembler;

import com.ning.application.dto.PaperQuestionDTO;
import com.ning.domain.entity.PaperQuestion;
import com.ning.infrastructure.common.model.PageWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 试卷试题实体、DTO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-10-27 11:00
 */
@Mapper
public interface PaperQuestionAssembler {

    PaperQuestionAssembler INSTANCE = Mappers.getMapper(PaperQuestionAssembler.class);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "paperId", source = "paperId.value")
    @Mapping(target = "questionId", source = "questionId.value")
    PaperQuestionDTO toDTO(PaperQuestion entity);

    List<PaperQuestionDTO> toDTOList(List<PaperQuestion> entityList);

    PageWrapper<PaperQuestionDTO> toDTOPageList(PageWrapper<PaperQuestion> pageEntityList);

}
