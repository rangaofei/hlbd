package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.Team;

import java.util.List;

public interface TeamDao {
    Team createTeam(Team team);

    Team findTeamById(int id);

    Team findTeamByTeamId(String teamId);

    Team saveTeamByTeacher(String teacherId, Team team);


}
