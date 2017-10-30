package com.hanlinbode.hlbd.composbean;

import com.hanlinbode.hlbd.bean.TeacherHomeWork;
import com.hanlinbode.hlbd.bean.Team;

import java.util.List;

public class CreateHomeWork {
    private TeacherHomeWork teacherHomeWork;
    private List<Team> teams;

    public TeacherHomeWork getTeacherHomeWork() {
        return teacherHomeWork;
    }

    public void setTeacherHomeWork(TeacherHomeWork teacherHomeWork) {
        this.teacherHomeWork = teacherHomeWork;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
