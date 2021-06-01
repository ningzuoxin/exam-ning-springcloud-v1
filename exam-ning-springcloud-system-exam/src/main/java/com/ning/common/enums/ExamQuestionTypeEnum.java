package com.ning.common.enums;

import java.util.*;

public enum ExamQuestionTypeEnum {

    CHOICE(0, "choice", "单选题"),
    CHOICE_MULTI(1, "choice_multi", "多选题"),
    TRUE_FALSE(2, "true_false", "判断题"),
    FILL_BLANK(3, "fill_blank", "填空题"),
    QUESTION(4, "question", "问答题");

    private int index;
    private String type;
    private String title;

    ExamQuestionTypeEnum(int index, String type, String title) {
        this.index = index;
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

    public static int getIndex(String type) {
        for (ExamQuestionTypeEnum en : values()) {
            if (en.type.equals(type)) {
                return en.index;
            }
        }
        return -1;
    }

}
