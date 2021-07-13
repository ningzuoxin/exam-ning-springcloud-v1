package com.ning.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 试卷批阅状态 doing、paused、reviewing、finished
 */
public enum ExamTestPaperResultStatusEnum {

    DOING("doing", "即将开始"),
    PAUSED("paused", "暂停阅卷"),
    REVIEWING("reviewing", "批阅中"),
    FINISHED("finished", "阅卷结束");

    private String type;
    private String title;

    ExamTestPaperResultStatusEnum(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public static List<Map<String, String>> listExamTestPaperTypes() {
        List<Map<String, String>> types = new ArrayList<>();
        for (ExamTestPaperResultStatusEnum value : values()) {
            Map<String, String> map = new HashMap<>();
            map.put("type", value.type);
            map.put("title", value.title);
            types.add(map);
        }
        return types;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
