package com.ning.domain.entity;

import com.ning.domain.enums.PaperResultStatusEnum;
import com.ning.domain.types.PaperId;
import com.ning.domain.types.PaperResultId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

/**
 * 试卷结果实体
 *
 * @author zuoxin.ning
 * @since 2024-11-27 15:30
 */
@Getter
@Setter
@ToString
public class PaperResult {

    // 试卷结果 ID
    private PaperResultId id;
    // 试卷 ID
    private PaperId paperId;
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

    public PaperResult(PaperResultId id, PaperId paperId, String paperTitle, Long userUid, Integer timeUsed) {
        this.id = id;
        this.paperId = paperId;
        this.paperTitle = paperTitle;
        this.userUid = userUid;
        this.timeUsed = timeUsed;
        this.status = PaperResultStatusEnum.PENDING.getValue();
    }

    public void finishGrading(Float score, int rightCount) {
        this.score = this.score + score;
        this.subjectiveScore = this.subjectiveScore + score;
        this.rightCount = this.rightCount + rightCount;
        this.status = PaperResultStatusEnum.COMPLETED.getValue();
        this.checkTime = Instant.now();
    }

}
