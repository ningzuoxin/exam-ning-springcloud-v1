package com.ning.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ExamTestPaperTypeEnum {

    TRAINING("training", "日常练习"),
    MOCK("mock", "模拟考试"),
    FORMAL("formal", "正式考试");

    private String type;
    private String title;

    ExamTestPaperTypeEnum(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public static List<Map<String, String>> listExamTestPaperTypes() {
        List<Map<String, String>> types = new ArrayList<>();
        for (ExamTestPaperTypeEnum value : values()) {
            Map<String, String> map = new HashMap<>();
            map.put("type", value.type);
            map.put("title", value.title);
            types.add(map);
        }
        return types;
    }

}
