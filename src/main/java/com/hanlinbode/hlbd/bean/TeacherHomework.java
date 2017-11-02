package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"id", "teacher"})
@Entity
public class TeacherHomework implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String homeworkId;
    @Column(nullable = false)
    private int type;
    @Column(nullable = false)
    private String name;

    private String subjectName;
    private int commitedCount;
    private int totalStudent;

    private int questionCount;

    private float correctRate;
    private float difficult;

    private boolean awaitCorrect;
    private String teacherId;

    @Column
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "team_homework",
            joinColumns = {@JoinColumn(name = "homework_id", referencedColumnName = "homeworkId")},
            inverseJoinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "teamId")})
    private List<Team> teamList;
    @Transient
    private List<TeacherHomeworkQuestion> teacherHomeworkQuestions;

    public List<TeacherHomeworkQuestion> getTeacherHomeworkQuestions() {
        return teacherHomeworkQuestions;
    }

    public void setTeacherHomeworkQuestions(List<TeacherHomeworkQuestion> teacherHomeworkQuestions) {
        this.teacherHomeworkQuestions = teacherHomeworkQuestions;
    }

    @JsonBackReference(value = "homework id")
    public int getId() {
        return id;
    }

    @JsonBackReference(value = "homework id")
    public void setId(int id) {
        this.id = id;
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }


    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public int getCommitedCount() {
        return commitedCount;
    }

    public void setCommitedCount(int commitedCount) {
        this.commitedCount = commitedCount;
    }

    public float getCorrectRate() {
        return correctRate;
    }

    public void setCorrectRate(float correctRate) {
        this.correctRate = correctRate;
    }

    public float getDifficult() {
        return difficult;
    }

    public void setDifficult(float difficult) {
        this.difficult = difficult;
    }

    public boolean isAwaitCorrect() {
        return awaitCorrect;
    }

    public void setAwaitCorrect(boolean awaitCorrect) {
        this.awaitCorrect = awaitCorrect;
    }

    @Override
    public String toString() {
        return "TeacherHomework{" +
                "UID=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", createdTime='" + createdTime + '\'' +
                '}';
    }

    public int getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(int totalStudent) {
        this.totalStudent = totalStudent;
    }


    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


}
