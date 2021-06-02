package com.ning.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * 阅卷开始事件
 */
public class MarkTestPaperStartEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;
    // 试卷结果id
    private Integer testPaperResultId;

    public MarkTestPaperStartEvent(Object source, Integer testPaperResultId) {
        super(source);
        this.testPaperResultId = testPaperResultId;
    }

    public Integer getTestPaperResultId() {
        return testPaperResultId;
    }

    public void setTestPaperResultId(Integer testPaperResultId) {
        this.testPaperResultId = testPaperResultId;
    }
}
