package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.Team;

import java.util.List;

public interface TeamService {
    Team createTeam(Team team);

    Team findTeamById(int id);

    Team findTeamByTeamId(String teamId);

    Team saveTeamByTeacher(String teacherId, Team team);

    List<Team> findTeamsByTeacherId(String teacherId);

    Team saveTeam(Team team);

    Team getTeamInfo(String teamId);

    int getTeamVolume(Team team);

    int getTeamsVolume(List<Team> teamList);

    List<Team> saveTeacherHomework(List<Team> team, TeacherHomework teacherHomework);

    List<Team> findTeamsByTeams(List<Team> teams);

}
