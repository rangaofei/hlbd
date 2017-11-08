package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hanlinbode.hlbd.enums.AnswerState;

import javax.persistence.*;

@Entity
public class TeacherHomeworkQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int stageId;
    @Column(nullable = false)
    private int subjectId;
    @Column(nullable = false)
    private int versionId;
    @Column(nullable = false)
    private int textbookId;
    @Column(nullable = false)
    private int booknodeId;
    @Column(nullable = false)
    private int questionId;//答案选项用的此id
    @Column(nullable = false)
    private int questiontypeId;

    private float correctRate;
    @Enumerated(EnumType.STRING)
    private AnswerState state = AnswerState.NOT_COMMIT;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String teacherHomeworkId;

    public String getTeacherHomeworkId() {
        return teacherHomeworkId;
    }

    public void setTeacherHomeworkId(String teacherHomeworkId) {
        this.teacherHomeworkId = teacherHomeworkId;
    }

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int xksubjectId) {
        this.subjectId = xksubjectId;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int xkversionId) {
        this.versionId = xkversionId;
    }

    public int getTextbookId() {
        return textbookId;
    }

    public void setTextbookId(int xkTextbookId) {
        this.textbookId = xkTextbookId;
    }

    public int getBooknodeId() {
        return booknodeId;
    }

    public void setBooknodeId(int booknodeId) {
        this.booknodeId = booknodeId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestiontypeId() {
        return questiontypeId;
    }

    public AnswerState getState() {
        return state;
    }

    public void setState(AnswerState state) {
        this.state = state;
    }

    public float getCorrectRate() {
        return correctRate;
    }

    public void setCorrectRate(float correctRate) {
        this.correctRate = correctRate;
    }


    public void setQuestiontypeId(int questiontypeId) {
        this.questiontypeId = questiontypeId;
    }

    @Override
    public String toString() {
        return "TeacherHomeworkQuestion{" +
                "id=" + id +
                ", stageId=" + stageId +
                ", subjectId=" + subjectId +
                ", versionId=" + versionId +
                ", textbookId=" + textbookId +
                ", booknodeId=" + booknodeId +
                ", questionId=" + questionId +
                ", questiontypeId=" + questiontypeId +
                ", correctRate=" + correctRate +
                ", teacherHomeworkId='" + teacherHomeworkId + '\'' +
                '}';
    }
}
