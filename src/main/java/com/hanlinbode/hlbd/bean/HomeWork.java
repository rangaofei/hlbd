package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"id", "fk_teacher_id", "answers", "teamList", "teacher"})
@Entity
public class HomeWork implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String homeworkId;
    @Column(nullable = false)
    private int type;
    @Column(nullable = false)
    private String name;
    @Column
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @Column(insertable = false, updatable = false)
    public String fk_teacher_id;

    @OneToMany(mappedBy = "answerHomeWork", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answers;



    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "team_homework",
            joinColumns = {@JoinColumn(name = "homework_id", referencedColumnName = "homeworkId")},
            inverseJoinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "teamId")})
    private List<Team> teamList;

    @ManyToOne
    @JoinColumn(name = "fk_teacher_id", referencedColumnName = "teacherId")
    private Teacher teacherHomeWork;

    @OneToMany(mappedBy = "homeWork", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InfoQuestion> infoQuestions;

    public List<InfoQuestion> getInfoQuestions() {
        return infoQuestions;
    }

    public void setInfoQuestions(List<InfoQuestion> infoQuestions) {
        this.infoQuestions = infoQuestions;
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

    public Teacher getTeacherHomeWork() {
        return teacherHomeWork;
    }

    public void setTeacherHomeWork(Teacher teacherHomeWork) {
        this.teacherHomeWork = teacherHomeWork;
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
    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getFkTeacherId() {
        return fk_teacher_id;
    }

    public void setFkTeacherId(String teacherId) {
        this.fk_teacher_id = teacherId;
    }

    @Override
    public String toString() {
        return "HomeWork{" +
                "UID=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", createdTime='" + createdTime + '\'' +
                '}';
    }
}
