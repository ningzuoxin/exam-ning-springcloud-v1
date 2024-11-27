package com.ning.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 试卷类型枚举
 *
 * @author zuoxin.ning
 * @since 2024-11-27 11:00
 */
@Getter
@AllArgsConstructor
public enum PaperTypeEnum {

    FORMAL(1, "正式"),
    MOCK(2, "模拟"),
    PRACTICE(3, "练习"),
    ;

    private final Integer type;
    private final String title;

    public static List<Integer> validTypeList() {
        return Arrays.stream(values()).map(PaperTypeEnum::getType).collect(Collectors.toList());
    }

}
