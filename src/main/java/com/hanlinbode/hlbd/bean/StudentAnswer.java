package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties({"id", "fk_homework_id", "fk_student_id", "studentAnswerLists"})
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

    @Column
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    private int type;
    private int qustionCount;
    private int state;
    private float correctRate;
    private String subjecName;
    private int studentCount;
    private int commitedStudentCount;
    private float difficult;

    private int seconds;

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

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

    @JsonBackReference(value = "10")
    public TeacherHomeWork getAnswerTeacherHomeWork() {
        return answerTeacherHomeWork;
    }

    @JsonBackReference(value = "10")
    public void setAnswerTeacherHomeWork(TeacherHomeWork answerTeacherHomeWork) {
        this.answerTeacherHomeWork = answerTeacherHomeWork;
    }


    public void initWithHomeWork(TeacherHomeWork teacherHomeWork) {
        this.setAnswerId(UUIDUtil.generateId());
        this.setAnswerTeacherHomeWork(teacherHomeWork);
        this.setAnswerName(teacherHomeWork.getName());
        this.setType(teacherHomeWork.getType());
        this.setSubjecName(teacherHomeWork.getSubjectName());
        this.setCreatedTime(teacherHomeWork.getCreatedTime());
        this.setQustionCount(teacherHomeWork.getTeacherHomeworkLists().size());
        this.setStudentCount(teacherHomeWork.getTotalStudent());
        this.setDifficult(teacherHomeWork.getDifficult());
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

    public float getDifficult() {
        return difficult;
    }

    public void setDifficult(float difficult) {
        this.difficult = difficult;
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
}
