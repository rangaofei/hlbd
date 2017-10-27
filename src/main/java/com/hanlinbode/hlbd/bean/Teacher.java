package com.hanlinbode.hlbd.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"id", "role", "teacherHomeWorks", "teams", "teacherSubjects", "teacherHomeWorks", "token"})
@Entity
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String teacherId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;


    @Column
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "teacherTeam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Team> teams;

    @OneToMany(mappedBy = "teacherSubject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TeacherSubject> teacherSubjects;

    @OneToMany(mappedBy = "teacherHomeWork", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TeacherHomeWork> teacherHomeWorks;


    public List<TeacherHomeWork> getTeacherHomeWorks() {
        return teacherHomeWorks;
    }

    public void setTeacherHomeWorks(List<TeacherHomeWork> teacherHomeWorks) {
        this.teacherHomeWorks = teacherHomeWorks;
    }

    public List<TeacherSubject> getTeacherSubjects() {
        return teacherSubjects;
    }

    public void setTeacherSubjects(List<TeacherSubject> teacherSubjects) {
        this.teacherSubjects = teacherSubjects;
    }

    public Teacher() {
        role = "T";
    }

    public Teacher(String teacherId) {
        this.teacherId = teacherId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherId='" + teacherId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", createdTime=" + createdTime +
                ", role='" + role + '\'' +

                '}';
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
