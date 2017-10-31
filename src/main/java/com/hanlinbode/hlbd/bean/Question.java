package com.hanlinbode.hlbd.bean;

import javax.persistence.*;

@Entity
@Table(name = "l_question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String answer;
    private int bookNodeId;
    private int difficult;
    private String questionAnalyze;
    private int questionTypeId;
    private String stem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getBookNodeId() {
        return bookNodeId;
    }

    public void setBookNodeId(int bookNodeId) {
        this.bookNodeId = bookNodeId;
    }

    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }

    public String getQuestionAnalyze() {
        return questionAnalyze;
    }

    public void setQuestionAnalyze(String questionAnalyze) {
        this.questionAnalyze = questionAnalyze;
    }

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(int questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }
}
