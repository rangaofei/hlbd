package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hanlinbode.hlbd.responsebean.Token;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date crteatedTime;

    @Column(nullable = false)
    private String role;

    @Transient
    private Token token;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answers;

    @JsonBackReference(value = "student ans")
    public List<Answer> getAnswers() {
        return answers;
    }

    @JsonBackReference(value = "student ans")
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "team_student",
            joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "studentId")},
            inverseJoinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "teamId")})
    private List<Team> teamList;


    public Student() {
        this.role = "S";
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

    public Date getCrteatedTime() {
        return crteatedTime;
    }

    public void setCrteatedTime(Date crteatedTime) {
        this.crteatedTime = crteatedTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @JsonBackReference(value = "student id")
    public int getId() {
        return id;
    }

    @JsonBackReference(value = "student id")
    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", crteatedTime=" + crteatedTime +
                ", role='" + role + '\'' +
                ", token=" + token +
                '}';
    }
}
