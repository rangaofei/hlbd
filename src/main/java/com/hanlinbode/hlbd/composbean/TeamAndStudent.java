package com.hanlinbode.hlbd.composbean;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;

import java.util.List;

public class TeamAndStudent {
    private Team team;
    private List<Student> studentList;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
