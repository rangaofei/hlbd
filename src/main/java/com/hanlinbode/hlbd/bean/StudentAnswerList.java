package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"studentAnswer"})
@Entity
public class StudentAnswerList {
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

    private String answer;
    private String answerImg;
    private String answerComment;
    private String correct;
    private String correctComment;

    @ManyToOne
    private StudentAnswer studentAnswer;

    public StudentAnswerList() {
    }

    public StudentAnswerList(TeacherHomeworkList teacherHomeworkList) {
        this.stageId = teacherHomeworkList.getStageId();
        this.subjectId = teacherHomeworkList.getSubjectId();
        this.versionId = teacherHomeworkList.getVersionId();
        this.textbookId = teacherHomeworkList.getTextbookId();
        this.booknodeId = teacherHomeworkList.getBooknodeId();
        this.questionId = teacherHomeworkList.getQuestionId();
        this.questiontypeId = teacherHomeworkList.getQuestiontypeId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public int getTextbookId() {
        return textbookId;
    }

    public void setTextbookId(int textbookId) {
        this.textbookId = textbookId;
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

    public StudentAnswer getStudentAnswer() {
        return studentAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerComment() {
        return answerComment;
    }

    public void setAnswerComment(String answerComment) {
        this.answerComment = answerComment;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getCorrectComment() {
        return correctComment;
    }

    public void setCorrectComment(String correctComment) {
        this.correctComment = correctComment;
    }

    public void setStudentAnswer(StudentAnswer studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public String getAnswerImg() {
        return answerImg;
    }

    public void setAnswerImg(String answerImg) {
        this.answerImg = answerImg;
    }
}
