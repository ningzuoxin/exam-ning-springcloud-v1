package com.ning.common.listener;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.ning.common.enums.ExamQuestionTypeEnum;
import com.ning.common.event.MarkTestPaperStartEvent;
import com.ning.common.mark.CheckHandler;
import com.ning.entity.ExamQuestion;
import com.ning.entity.ExamTestPaperItem;
import com.ning.entity.ExamTestPaperItemResult;
import com.ning.entity.ExamTestPaperResult;
import com.ning.manager.ExamQuestionManager;
import com.ning.manager.ExamTestPaperItemManager;
import com.ning.manager.ExamTestPaperItemResultManager;
import com.ning.manager.ExamTestPaperResultManager;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MarkTestPaperStartEventListener implements ApplicationListener<MarkTestPaperStartEvent> {

    @Resource
    CheckHandler checkHandler;
    @Resource
    ExamQuestionManager examQuestionManager;
    @Resource
    ExamTestPaperItemManager examTestPaperItemManager;
    @Resource
    ExamTestPaperResultManager examTestPaperResultManager;
    @Resource
    ExamTestPaperItemResultManager examTestPaperItemResultManager;

    @Override
    public void onApplicationEvent(MarkTestPaperStartEvent markTestPaperStartEvent) {
        Integer testPaperResultId = markTestPaperStartEvent.getTestPaperResultId();
        System.out.println("MarkTestPaperStartEventListener # onApplicationEvent testPaperResultId=" + testPaperResultId);

        ExamTestPaperResult examTestPaperResult = examTestPaperResultManager.selectById(testPaperResultId);
        // 所有试卷项目
        List<ExamTestPaperItem> examTestPaperItems = examTestPaperItemManager.listByTestPaperId(examTestPaperResult.getTestpaperId());
        // 所有试题
        List<ExamQuestion> examQuestions = examQuestionManager.selectBatchIds(examTestPaperItems.stream().map(m -> m.getQuestionId()).collect(Collectors.toList()));
        // 所有试卷项目结果
        List<ExamTestPaperItemResult> examTestPaperItemResults = examTestPaperItemResultManager.selectByExamTestPaperItemResultId(examTestPaperResult.getId());

        Float score = 0F;
        int rightItemCount = 0; // 正确题数
        for (ExamTestPaperItemResult examTestPaperItemResult : examTestPaperItemResults) {
            ExamTestPaperItem examTestPaperItem = examTestPaperItems.stream().filter(t -> NumberUtil.compare(t.getId(), examTestPaperItemResult.getTestpaperItemId()) == 0).findAny().get();
            ExamQuestion examQuestion = examQuestions.stream().filter(t -> NumberUtil.compare(t.getId(), examTestPaperItemResult.getQuestionId()) == 0).findAny().get();

            ExamTestPaperItemResult result = checkHandler.check(examTestPaperItemResult, examQuestion, examTestPaperItem);
            if (result != null) {
                if ("right".equals(result.getStatus())) {
                    rightItemCount++;
                }
                score += result.getScore();
                examTestPaperItemResultManager.updateById(result);
            }
        }

        int now = (int) DateUtil.currentSeconds();
        long count = examTestPaperItems.stream().filter(t -> ExamQuestionTypeEnum.QUESTION.getType().equals(t.getQuestionType())).count();

        examTestPaperResult.setScore(score);
        examTestPaperResult.setObjectiveScore(0F);
        examTestPaperResult.setSubjectiveScore(score);
        examTestPaperResult.setRightItemCount(rightItemCount);
        if (count == 0) { // 没有主观题
            examTestPaperResult.setStatus("finished");
        } else {
            examTestPaperResult.setStatus("reviewing");
        }
        examTestPaperResult.setUpdateTime(now);
        examTestPaperResultManager.updateById(examTestPaperResult);
    }

}
