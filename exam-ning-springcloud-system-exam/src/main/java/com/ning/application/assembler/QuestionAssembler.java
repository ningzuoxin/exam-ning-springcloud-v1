package com.ning.application.assembler;

import com.ning.application.dto.QuestionDTO;
import com.ning.domain.entity.Question;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.interfaces.request.AddQuestionRequest;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 试题实体、DTO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-10-17 09:00
 */
@Mapper
public interface QuestionAssembler {

    QuestionAssembler INSTANCE = Mappers.getMapper(QuestionAssembler.class);

    @Mapping(target = "id", source = "id.value")
    QuestionDTO toDTO(Question entity);

    List<QuestionDTO> toDTOList(List<Question> entityList);

    PageWrapper<QuestionDTO> toDTOPageList(PageWrapper<Question> pageEntityList);

    QuestionDTO toDTO(AddQuestionRequest request);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Question entity, QuestionDTO dto);

}
