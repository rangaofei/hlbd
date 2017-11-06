package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.Team;

import java.util.List;

public interface TeamService {
    Team createTeam(Team team);

    Team findTeamById(int id);

    Team findTeamByTeamId(String teamId);

    Team saveTeamByTeacher(String teacherId, Team team);

    List<Team> findTeamsByTeacherId(String teacherId);

}
