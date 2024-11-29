package com.ning.domain.event;

import com.ning.domain.entity.PaperQuestion;
import com.ning.domain.entity.PaperQuestionResult;
import com.ning.domain.entity.PaperResult;
import com.ning.domain.entity.Question;
import com.ning.domain.repository.PaperQuestionRepository;
import com.ning.domain.repository.PaperQuestionResultRepository;
import com.ning.domain.repository.PaperResultRepository;
import com.ning.domain.repository.QuestionRepository;
import com.ning.domain.service.PaperResultGradingService;
import com.ning.domain.types.QuestionId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 试卷相关事件
 *
 * @author zuoxin.ning
 * @since 2024-11-29 15:00
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PaperEventsProcessor {

    private final QuestionRepository questionRepository;
    private final PaperResultRepository paperResultRepository;
    private final PaperQuestionRepository paperQuestionRepository;
    private final PaperResultGradingService paperResultGradingService;
    private final PaperQuestionResultRepository paperQuestionResultRepository;

    /**
     * 处理试卷提交事件 - 阅卷
     *
     * @param event 试卷提交事件
     */
    @Async
    @EventListener
    public void handlePaperSubmitEventForGrading(PaperEvents.PaperSubmitEvent event) {
        log.info("event: {}.", event);

        Optional<PaperResult> paperResultOpt = paperResultRepository.find(event.getPaperResultId());
        if (!paperResultOpt.isPresent()) {
            log.warn("paper result not exits. id: {}.", event.getPaperResultId().getValue());
            return;
        }

        PaperResult paperResult = paperResultOpt.get();

        // 试卷试题列表
        List<PaperQuestion> paperQuestionList = paperQuestionRepository.find(paperResult.getPaperId());
        // 试卷试题结果列表
        List<PaperQuestionResult> paperQuestionResultList = paperQuestionResultRepository.find(paperResult.getId());
        // 试题列表
        List<QuestionId> questionIdList = paperQuestionList.stream().map(PaperQuestion::getQuestionId).collect(Collectors.toList());
        List<Question> questionList = questionRepository.find(questionIdList);

        // 阅卷
        paperResultGradingService.doGrading(paperResult, questionList, paperQuestionList, paperQuestionResultList);
    }

}
