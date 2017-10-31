package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"id", "teacherHomeWork"})
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

    private int correctRate;

    private boolean awaitCorrect;

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

    public boolean isAwaitCorrect() {
        return awaitCorrect;
    }

    public int getCorrectRate() {
        return correctRate;
    }

    public void setCorrectRate(int correctRate) {
        this.correctRate = correctRate;
    }

    public void setAwaitCorrect(boolean awaitCorrect) {
        this.awaitCorrect = awaitCorrect;
    }

    public void setQuestiontypeId(int questiontypeId) {
        this.questiontypeId = questiontypeId;
    }

    @Override
    public String toString() {
        return "TeacherHomeworkQuestion{" +
                "stageId=" + stageId +
                ", xksubjectId=" + subjectId +
                ", xkversionId=" + versionId +
                ", xkTextbookId=" + textbookId +
                ", booknodeId=" + booknodeId +
                ", questionId=" + questionId +
                ", questiontypeId=" + questiontypeId +
                '}';
    }
}
