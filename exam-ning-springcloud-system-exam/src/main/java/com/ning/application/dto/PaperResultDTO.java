package com.ning.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * 试卷结果 DTO
 *
 * @author zuoxin.ning
 * @since 2024-11-28 08:00
 */
@Getter
@Setter
public class PaperResultDTO {

    // 试卷结果 ID
    private Long id;
    // 试卷 ID
    private Long paperId;
    // 试卷标题
    private String paperTitle;
    // 答题者用户 ID
    private Long userUid;
    // 最终得分
    private Float score;
    // 客观题得分
    private Float objectiveScore;
    // 主观题得分
    private Float subjectiveScore;
    // 教师点评
    private String comments;
    // 答题正确数
    private Integer rightCount;
    // 答题耗时，单位：秒
    private Integer timeUsed;
    // 阅卷状态，0：待阅卷；1：阅卷中；2：暂停；3：阅卷结束；
    private Integer status;
    // 阅卷老师用户ID
    private Long checkUserUid;
    // 批卷时间
    private Instant checkTime;

}