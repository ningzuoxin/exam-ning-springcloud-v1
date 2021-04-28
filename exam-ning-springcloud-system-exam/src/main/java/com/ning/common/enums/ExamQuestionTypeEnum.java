package com.ning.common.enums;

import java.util.*;

public enum ExamQuestionTypeEnum {

    CHOICE("choice", "单选题"),
    CHOICE_MULTI("choice_multi", "多选题"),
    FILL_BLANK("fill_blank", "填空题"),
    TRUE_FALSE("true_false", "判断题"),
    QUESTION("question", "问答题");

    private String type;
    private String title;

    ExamQuestionTypeEnum(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public static List<Map<String, String>> listExamQuestionTypes() {
        List<Map<String, String>> types = new ArrayList<>();
        for (ExamQuestionTypeEnum value : values()) {
            Map<String, String> map = new HashMap<>();
            map.put("type", value.type);
            map.put("title", value.title);
            types.add(map);
        }
        return types;
    }

}
