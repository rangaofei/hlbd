package com.hanlinbode.hlbd.composbean;

import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.Team;

import java.util.List;

public class CreateHomeWork {
    private TeacherHomework teacherHomework;
    private List<Team> teams;

    public TeacherHomework getTeacherHomework() {
        return teacherHomework;
    }

    public void setTeacherHomework(TeacherHomework teacherHomework) {
        this.teacherHomework = teacherHomework;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
