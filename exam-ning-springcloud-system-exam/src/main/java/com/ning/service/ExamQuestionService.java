package com.ning.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.entity.ExamQuestion;
import com.ning.manager.ExamQuestionManager;
import com.ning.model.Result;
import com.ning.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ExamQuestionService {

    @Resource
    ExamQuestionManager examQuestionManager;

    /**
     * 分页查询考题列表
     *
     * @param type
     * @param keyword
     * @param pNum
     * @param pSize
     * @return
     */
    public Result<IPage<ExamQuestion>> selectExamQuestionPage(String type, String keyword, Integer pNum, Integer pSize) {
        return Result.ok(examQuestionManager.selectExamQuestionPage(type, keyword, pNum, pSize));
    }

    /**
     * 添加考题
     *
     * @param examQuestion
     * @return
     */
    public Result add(ExamQuestion examQuestion) {
        Integer result = examQuestionManager.add(examQuestion);
        if (result == 1) {
            return Result.ok(examQuestion);
        } else {
            return Result.fail("添加失败");
        }
    }

    /**
     * 修改考题显示状态
     *
     * @param id
     * @param isShow
     * @return
     */
    public Result updateIsShow(Integer id, Integer isShow) {
        if (isShow != 0 && isShow != 1) {
            return Result.fail("无效的显示状态");
        }

        ExamQuestion examQuestion = examQuestionManager.get(id);
        if (ObjectUtil.isEmpty(examQuestion)) {
            return Result.fail("不存在的考题");
        }
        if (examQuestion.getIsDelete() == 1) {
            return Result.fail("考题已被删除");
        }

        examQuestion.setIsShow(isShow);
        examQuestion.setUpdateTime((int) DateUtil.currentSeconds());
        examQuestion.setUpdateUserId(SecurityUtils.getLoginUser().getUserId().intValue());
        Integer result = examQuestionManager.update(examQuestion);
        if (result == 1) {
            return Result.ok(examQuestion);
        } else {
            return Result.fail("修改显示状态失败");
        }
    }

    /**
     * 删除考题
     *
     * @param id
     * @return
     */
    public Result delete(Integer id) {
        ExamQuestion examQuestion = examQuestionManager.get(id);
        if (ObjectUtil.isEmpty(examQuestion)) {
            return Result.fail("不存在的考题");
        }

        examQuestion.setIsDelete(1);
        examQuestion.setUpdateTime((int) DateUtil.currentSeconds());
        examQuestion.setUpdateUserId(SecurityUtils.getLoginUser().getUserId().intValue());
        Integer result = examQuestionManager.update(examQuestion);
        if (result == 1) {
            return Result.ok(examQuestion);
        } else {
            return Result.fail("删除失败");
        }
    }

    /**
     * 考题详情
     *
     * @param id
     * @return
     */
    public Result detail(Integer id) {
        ExamQuestion examQuestion = examQuestionManager.get(id);
        if (ObjectUtil.isEmpty(examQuestion)) {
            return Result.fail("不存在的考题");
        }
        return Result.ok(examQuestion);
    }

}
