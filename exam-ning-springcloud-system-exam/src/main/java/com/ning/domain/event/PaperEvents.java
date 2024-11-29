package com.ning.domain.event;

import cn.hutool.core.util.IdUtil;
import com.ning.domain.types.PaperResultId;
import lombok.Value;
import org.springframework.context.ApplicationEvent;

/**
 * 试卷相关事件
 *
 * @author zuoxin.ning
 * @since 2024-11-29 15:00
 */
public interface PaperEvents {

    /**
     * 试卷提交事件
     */
    @Value
    class PaperSubmitEvent extends ApplicationEvent {

        private static final long serialVersionUID = 8444320053013349800L;

        // 事件 ID
        String eventId;
        // 试卷结果 ID
        PaperResultId paperResultId;

        public PaperSubmitEvent(Object source, PaperResultId paperResultId) {
            super(source);
            this.eventId = IdUtil.simpleUUID();
            this.paperResultId = paperResultId;
        }
    }

}
