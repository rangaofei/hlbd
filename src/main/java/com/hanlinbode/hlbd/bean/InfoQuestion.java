package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"id"})
@Entity
public class InfoQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int stageId;
    @Column(nullable = false)
    private int xksubjectId;
    @Column(nullable = false)
    private int xkversionId;
    @Column(nullable = false)
    private int xkTextbookId;
    @Column(nullable = false)
    private int booknodeId;
    @Column(nullable = false)
    private int questionId;//答案选项用的此id
    @Column(nullable = false)
    private int questiontypeId;
    @ManyToOne
    @JoinColumn(name = "fk_homework_id",referencedColumnName = "homeworkId")
    private HomeWork homeWork;

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public int getXksubjectId() {
        return xksubjectId;
    }

    public void setXksubjectId(int xksubjectId) {
        this.xksubjectId = xksubjectId;
    }

    public int getXkversionId() {
        return xkversionId;
    }

    public void setXkversionId(int xkversionId) {
        this.xkversionId = xkversionId;
    }

    public int getXkTextbookId() {
        return xkTextbookId;
    }

    public void setXkTextbookId(int xkTextbookId) {
        this.xkTextbookId = xkTextbookId;
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
        return "InfoQuestion{" +
                "stageId=" + stageId +
                ", xksubjectId=" + xksubjectId +
                ", xkversionId=" + xkversionId +
                ", xkTextbookId=" + xkTextbookId +
                ", booknodeId=" + booknodeId +
                ", questionId=" + questionId +
                ", questiontypeId=" + questiontypeId +
                '}';
    }
}
