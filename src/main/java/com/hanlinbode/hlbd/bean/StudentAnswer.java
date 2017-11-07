package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hanlinbode.hlbd.enums.AnswerState;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties({"id", "studentAnswerLists", "fk_student_id"})
public class StudentAnswer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String answerId;

    private String answerName;

    @Column(nullable = false)
    private String homeworkId;
    @Column(nullable = false)
    private String studentId;
    @Column(nullable = false)
    private String studentName;
    @Column
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    private int type;
    private int questionCount;
    @Enumerated(EnumType.STRING)
    private AnswerState state = AnswerState.NOT_COMMIT;
    private float correctRate;
    private String subjectName;
    private int studentCount;
    private int commitedStudentCount;
    private float difficult;
    private String teamId;
    private String finishTime;
    private String costTime;

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    private int seconds;

    public int getSeconds() {
        return seconds;
    }


    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }


    @Transient
    private List<StudentAnswerQuestion> studentAnswerLists;

    public List<StudentAnswerQuestion> getStudentAnswerLists() {
        return studentAnswerLists;
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentAnswerLists(List<StudentAnswerQuestion> studentAnswerQuestions) {
        this.studentAnswerLists = studentAnswerQuestions;
    }


    public void initWithHomeWork(TeacherHomework teacherHomework) {
        this.setAnswerId(UUIDUtil.generateId());
        this.setHomeworkId(teacherHomework.getHomeworkId());
        this.setAnswerName(teacherHomework.getName());
        this.setType(teacherHomework.getType());
        this.setSubjectName(teacherHomework.getSubjectName());
        this.setCreatedTime(teacherHomework.getCreatedTime());
        this.setQuestionCount(teacherHomework.getTeacherHomeworkQuestions().size());
        this.setStudentCount(teacherHomework.getTotalStudent());
        this.setDifficult(teacherHomework.getDifficult());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }


    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getCorrectRate() {
        return correctRate;
    }

    public void setCorrectRate(float correctRate) {
        this.correctRate = correctRate;
    }

    public AnswerState getState() {
        return state;
    }

    public void setState(AnswerState state) {
        this.state = state;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getCommitedStudentCount() {
        return commitedStudentCount;
    }

    public void setCommitedStudentCount(int commitedStudentCount) {
        this.commitedStudentCount = commitedStudentCount;
    }

    public float getDifficult() {
        return difficult;
    }

    public void setDifficult(float difficult) {
        this.difficult = difficult;
    }

    @Override
    public String toString() {
        return "StudentAnswer{" +
                "id=" + id +
                ", answerId='" + answerId + '\'' +
                ", answerName='" + answerName + '\'' +
                ", homeworkId='" + homeworkId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", createdTime=" + createdTime +
                ", type=" + type +
                ", questionCount=" + questionCount +
                ", state=" + state +
                ", correctRate=" + correctRate +
                ", subjectName='" + subjectName + '\'' +
                ", studentCount=" + studentCount +
                ", commitedStudentCount=" + commitedStudentCount +
                ", difficult=" + difficult +
                ", teamId='" + teamId + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", costTime='" + costTime + '\'' +
                ", seconds=" + seconds +
                '}';
    }
}
