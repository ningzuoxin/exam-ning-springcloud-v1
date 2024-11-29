package com.ning.domain.service;

import com.ning.domain.entity.PaperQuestion;
import com.ning.domain.entity.PaperQuestionResult;
import com.ning.domain.entity.PaperResult;
import com.ning.domain.enums.PaperQuestionResultStatusEnum;
import com.ning.domain.repository.PaperQuestionResultRepository;
import com.ning.domain.repository.PaperResultRepository;
import com.ning.domain.types.QuestionId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 试卷结果评分领域服务
 *
 * @author zuoxin.ning
 * @since 2024-11-28 17:00
 */
@Service
@RequiredArgsConstructor
public class PaperResultGradingService {

    private final PaperResultRepository paperResultRepository;
    private final PaperQuestionResultRepository paperQuestionResultRepository;

    /**
     * 评分
     *
     * @param paperResult             试卷结果
     * @param paperQuestionList       试卷试题列表
     * @param paperQuestionResultList 试卷试题结果列表
     * @return 试卷结果
     */
    public PaperResult doGrading(PaperResult paperResult, List<PaperQuestion> paperQuestionList, List<PaperQuestionResult> paperQuestionResultList) {
        Map<QuestionId, PaperQuestion> paperQuestionMap = paperQuestionList.stream().collect(Collectors.toMap(PaperQuestion::getQuestionId, Function.identity()));

        AtomicReference<Float> score = new AtomicReference<>(0f);
        AtomicInteger rightCount = new AtomicInteger();
        paperQuestionResultList.forEach(r -> {
            PaperQuestion paperQuestion = paperQuestionMap.get(r.getQuestionId());
            r.grading(paperQuestion.getRightScore());
            score.updateAndGet(v -> v + r.getScore());
            if (PaperQuestionResultStatusEnum.CORRECT.getValue() == r.getStatus()) {
                rightCount.getAndIncrement();
            }
            paperQuestionResultRepository.save(r);
        });

        // 阅卷结束
        paperResult.finishGrading(score.get(), rightCount.get());
        paperResultRepository.save(paperResult);
        return paperResult;
    }

}
