package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"id", "fk_homework_id", "fk_student_id", "studentAnswerLists"})
@Entity
public class StudentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String answerId;

    private String answerName;
    @ManyToOne
    @JoinColumn(name = "fk_student_id", referencedColumnName = "studentId")
    private Student student;
    @Column(insertable = false, updatable = false)
    private String fk_homework_id;
    @Column(insertable = false, updatable = false)
    private String fk_student_id;

    public String getFk_homework_id() {
        return fk_homework_id;
    }

    public void setFk_homework_id(String homeWorkId) {
        this.fk_homework_id = homeWorkId;
    }

    public String getFk_student_id() {
        return fk_student_id;
    }

    public void setFk_student_id(String studentId) {
        this.fk_student_id = studentId;
    }

    private String createdTime;

    private int type;
    private int qustionCount;
    private int state;
    private float correctRate;
    private String subjecName;
    private int studentCount;
    private int commitedStudentCount;
    @ManyToOne
    @JoinColumn(name = "fk_homework_id", referencedColumnName = "homeworkId")
    private TeacherHomeWork answerTeacherHomeWork;
    @OneToMany(mappedBy = "studentAnswer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentAnswerList> studentAnswerLists;

    public List<StudentAnswerList> getStudentAnswerLists() {
        return studentAnswerLists;
    }

    public void setStudentAnswerLists(List<StudentAnswerList> studentAnswerLists) {
        this.studentAnswerLists = studentAnswerLists;
    }

    @JsonBackReference(value = "10")
    public TeacherHomeWork getAnswerTeacherHomeWork() {
        return answerTeacherHomeWork;
    }

    @JsonBackReference(value = "10")
    public void setAnswerTeacherHomeWork(TeacherHomeWork answerTeacherHomeWork) {
        this.answerTeacherHomeWork = answerTeacherHomeWork;
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

    @JsonBackReference(value = "11")
    public Student getStudent() {
        return student;
    }

    @JsonBackReference(value = "11")
    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "id=" + id +
                ", AnswerId='" + answerId + '\'' +
                ", answerName='" + answerName + '\'' +
                ", student=" + student +
                ", createdTime='" + createdTime + '\'' +
                '}';
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getQustionCount() {
        return qustionCount;
    }

    public void setQustionCount(int qustionCount) {
        this.qustionCount = qustionCount;
    }

    public String getSubjecName() {
        return subjecName;
    }

    public void setSubjecName(String subjecName) {
        this.subjecName = subjecName;
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
}
