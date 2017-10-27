package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"id", "teacherHomeWork"})
@Entity
public class TeacherHomeworkList {
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
    @ManyToOne
    @JoinColumn(name = "fk_homework_id", referencedColumnName = "homeworkId")
    private TeacherHomeWork teacherHomeWork;

    public TeacherHomeWork getTeacherHomeWork() {
        return teacherHomeWork;
    }

    public void setTeacherHomeWork(TeacherHomeWork teacherHomeWork) {
        this.teacherHomeWork = teacherHomeWork;
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

    public void setQuestiontypeId(int questiontypeId) {
        this.questiontypeId = questiontypeId;
    }

    @Override
    public String toString() {
        return "TeacherHomeworkList{" +
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
