package com.ning.application.service;

import com.ning.application.dto.QuestionTypeDTO;
import com.ning.domain.enums.QuestionTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 试题应用服务
 *
 * @author zuoxin.ning
 * @since 2024-11-21 15:00
 */
@Service
@RequiredArgsConstructor
public class QuestionAppService {

    /**
     * 查询试题类型列表
     *
     * @return 试题类型列表
     */
    public List<QuestionTypeDTO> findTypeList() {
        return Arrays.stream(QuestionTypeEnum.values())
                .map(t -> QuestionTypeDTO.builder().type(t.getType()).title(t.getTitle()).build())
                .collect(Collectors.toList());
    }

}
