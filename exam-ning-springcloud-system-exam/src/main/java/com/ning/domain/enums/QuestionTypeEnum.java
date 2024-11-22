package com.ning.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 试题类型枚举
 *
 * @author zuoxin.ning
 * @since 2024-11-21 15:00
 */
@Getter
@AllArgsConstructor
public enum QuestionTypeEnum {

    SINGLE_CHOICE(0, "SINGLE_CHOICE", "单选题"),
    MULTIPLE_CHOICE(1, "MULTIPLE_CHOICE", "多选题"),
    TRUE_FALSE(2, "TRUE_FALSE", "判断题"),
    FILL_IN_BLANK(3, "FILL_IN_BLANK", "填空题"),
    SHORT_ANSWER(4, "SHORT_ANSWER", "问答题"),
    ;

    private final int index;
    private final String type;
    private final String title;

    public static int index(String type) {
        for (QuestionTypeEnum e : values()) {
            if (Objects.equals(e.getType(), type)) {
                return e.getIndex();
            }
        }
        return -1;
    }

    public static List<String> validTypeList() {
        return Arrays.stream(values()).map(QuestionTypeEnum::getType).collect(Collectors.toList());
    }

}
