package com.ning.application.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 试题选项 DTO
 *
 * @author zuoxin.ning
 * @since 2024-11-21 15:00
 */
@Getter
@Setter
public class QuestionOptionDTO {

    // 选项 ID
    private String id;
    // 选项内容
    private String text;

}
