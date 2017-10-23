package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class Team implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String teamId;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    private String teamIntroduction;

    @ManyToOne
    @JoinColumn(name = "teacher_id",referencedColumnName = "teacherId")
    private Teacher teacherTeam;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "team_student",
            joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "teamId")},
            inverseJoinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "studentId")})
    private List<Student> students;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "team_homework",
            joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "teamId")},
            inverseJoinColumns = {@JoinColumn(name = "homework_id", referencedColumnName = "homeworkId")})
    private List<HomeWork> homeWorkList;


    public List<HomeWork> getHomeWorkList() {
        return homeWorkList;
    }

    public void setHomeWorkList(List<HomeWork> homeWorkList) {
        this.homeWorkList = homeWorkList;
    }
    @JsonIgnore
    public List<Student> getStudents() {
        return students;
    }
    @JsonIgnore
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @JsonBackReference(value = "team id")
    public int getId() {
        return id;
    }

    @JsonBackReference(value = "team id")
    public void setId(int id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamIntroduction() {
        return teamIntroduction;
    }

    public void setTeamIntroduction(String teamIntroduction) {
        this.teamIntroduction = teamIntroduction;
    }


    @JsonBackReference(value = "2")
    public Teacher getTeacher() {
        return teacherTeam;
    }

    @JsonBackReference(value = "2")
    public void setTeacher(Teacher teacher) {
        this.teacherTeam = teacher;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", teamIntroduction='" + teamIntroduction + '\'' +
                ", students=" + students +
                ", homeWorkList=" + homeWorkList +
                '}';
    }
}
