package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hanlinbode.hlbd.enums.AnswerState;

import javax.persistence.*;

@JsonIgnoreProperties({"studentAnswer"})
@Entity
public class StudentAnswerQuestion {
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

    private int score;
    private String answer;
    private String answerImg;
    private String answerComment;
    private String correct;
    private String correctComment;
    @Enumerated(EnumType.STRING)
    private AnswerState state = AnswerState.NOT_COMMIT;
    private String studentId;
    private String studentName;
    private int teacherHomeworkQuestionId;
    private String answerId;

    public String getStudentId() {
        return studentId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getTeacherHomeworkQuestionId() {
        return teacherHomeworkQuestionId;
    }

    public void setTeacherHomeworkQuestionId(int teacherHomeworkQuestionId) {
        this.teacherHomeworkQuestionId = teacherHomeworkQuestionId;
    }

    public AnswerState getState() {
        return state;
    }

    public void setState(AnswerState state) {
        this.state = state;
    }

    public StudentAnswerQuestion() {
    }


    public StudentAnswerQuestion(TeacherHomeworkQuestion teacherHomeworkQuestion) {
        this.stageId = teacherHomeworkQuestion.getStageId();
        this.subjectId = teacherHomeworkQuestion.getSubjectId();
        this.versionId = teacherHomeworkQuestion.getVersionId();
        this.textbookId = teacherHomeworkQuestion.getTextbookId();
        this.booknodeId = teacherHomeworkQuestion.getBooknodeId();
        this.questionId = teacherHomeworkQuestion.getQuestionId();
        this.questiontypeId = teacherHomeworkQuestion.getQuestiontypeId();
        this.teacherHomeworkQuestionId = teacherHomeworkQuestion.getId();
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


    public String getAnswerImg() {
        return answerImg;
    }

    public void setAnswerImg(String answerImg) {
        this.answerImg = answerImg;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentAnswerQuestion{" +
                "id=" + id +
                ", stageId=" + stageId +
                ", subjectId=" + subjectId +
                ", versionId=" + versionId +
                ", textbookId=" + textbookId +
                ", booknodeId=" + booknodeId +
                ", questionId=" + questionId +
                ", questiontypeId=" + questiontypeId +
                ", score=" + score +
                ", answer='" + answer + '\'' +
                ", answerComment='" + answerComment + '\'' +
                ", correct='" + correct + '\'' +
                ", correctComment='" + correctComment + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", teacherHomeworkQuestionId=" + teacherHomeworkQuestionId +
                ", answerId='" + answerId + '\'' +
                '}';
    }
}
