package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.repository.Query;

import javax.json.Json;
import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String answerId;

    private String AnswerContent;
    @ManyToOne
    @JoinColumn(name = "fk_student_id", referencedColumnName = "studentId")
    private Student student;

//    @ManyToOne
//    @JoinColumn(name = "fk_team_id", referencedColumnName = "teamId")
//    private Team team;
//
//    public Team getTeam() {
//        return team;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//    }

    private String answerUrl;
    @ManyToOne
    @JoinColumn(name = "fk_homework_id", referencedColumnName = "homeworkId")
    private HomeWork answerHomeWork;
    @JsonBackReference(value = "10")
    public HomeWork getAnswerHomeWork() {
        return answerHomeWork;
    }
    @JsonBackReference(value = "10")
    public void setAnswerHomeWork(HomeWork answerHomeWork) {
        this.answerHomeWork = answerHomeWork;
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

    public String getAnswerContent() {
        return AnswerContent;
    }

    public void setAnswerContent(String answerContent) {
        AnswerContent = answerContent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getAnswerUrl() {
        return answerUrl;
    }

    public void setAnswerUrl(String answerUrl) {
        this.answerUrl = answerUrl;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "id=" + id +
                ", AnswerId='" + answerId + '\'' +
                ", AnswerContent='" + AnswerContent + '\'' +
                ", student=" + student +
                ", answerUrl='" + answerUrl + '\'' +
                '}';
    }
}
