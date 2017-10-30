package com.hanlinbode.hlbd.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"id", "fk_teacher_id", "teamList", "teacher"})
@Entity
public class TeacherHomeWork implements Serializable {
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

    private int totalStudent;

    public int getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(int totalStudent) {
        this.totalStudent = totalStudent;
    }

    @Column
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @Column(insertable = false, updatable = false)
    public String fk_teacher_id;

    @OneToMany(mappedBy = "answerTeacherHomeWork", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StudentAnswer> studentAnswers;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "team_homework",
            joinColumns = {@JoinColumn(name = "homework_id", referencedColumnName = "homeworkId")},
            inverseJoinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "teamId")})
    private List<Team> teamList;

    @ManyToOne
    @JoinColumn(name = "fk_teacher_id", referencedColumnName = "teacherId")
    private Teacher teacherHomeWork;

    @OneToMany(mappedBy = "teacherHomeWork", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TeacherHomeworkList> teacherHomeworkLists;

    public List<TeacherHomeworkList> getTeacherHomeworkLists() {
        return teacherHomeworkLists;
    }

    public void setTeacherHomeworkLists(List<TeacherHomeworkList> teacherHomeworkLists) {
        this.teacherHomeworkLists = teacherHomeworkLists;
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

    @JsonBackReference(value = "3")
    public Teacher getTeacherHomeWork() {
        return teacherHomeWork;
    }

    @JsonBackReference(value = "3")
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

    public List<StudentAnswer> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(List<StudentAnswer> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }

    public String getFkTeacherId() {
        return fk_teacher_id;
    }

    public void setFkTeacherId(String teacherId) {
        this.fk_teacher_id = teacherId;
    }

    @Override
    public String toString() {
        return "TeacherHomeWork{" +
                "UID=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", createdTime='" + createdTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherHomeWork that = (TeacherHomeWork) o;

        if (type != that.type) return false;
        if (homeworkId != null ? !homeworkId.equals(that.homeworkId) : that.homeworkId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        return fk_teacher_id != null ? fk_teacher_id.equals(that.fk_teacher_id) : that.fk_teacher_id == null;
    }

    @Override
    public int hashCode() {
        int result = homeworkId != null ? homeworkId.hashCode() : 0;
        result = 31 * result + type;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (fk_teacher_id != null ? fk_teacher_id.hashCode() : 0);
        return result;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
