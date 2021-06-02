package com.ning.manager;

import com.ning.common.enums.ExamQuestionTypeEnum;
import com.ning.entity.ExamTestPaperItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ExamTestPaperItemManagerTest {

    @Autowired
    ExamTestPaperItemManager examTestPaperItemManager;

    @Test
    void listByTestPaperId() {
        List<ExamTestPaperItem> examTestPaperItems = examTestPaperItemManager.listByTestPaperId(107);
        examTestPaperItems.sort((t1, t2) -> {
            int i1 = ExamQuestionTypeEnum.getIndex(t1.getQuestionType());
            int i2 = ExamQuestionTypeEnum.getIndex(t2.getQuestionType());
            return i1 - i2;
        });
        for (ExamTestPaperItem examTestPaperItem : examTestPaperItems) {
            System.out.println(examTestPaperItem);
        }
    }

    @Test
    void testFindAny() {
        List<ExamTestPaperItem> examTestPaperItems = examTestPaperItemManager.listByTestPaperId(107);
        ExamTestPaperItem examTestPaperItem = examTestPaperItems.stream().filter(t -> t.getId() == 1830).findAny().get();
        System.out.println(examTestPaperItem);
    }
}
