package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.dao.TeamRepository;
import com.hanlinbode.hlbd.exception.ParamIncorrectException;
import com.hanlinbode.hlbd.exception.ResultNotFoundException;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;


    @Override
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team findTeamById(int id) {
        return teamRepository.findTeamById(id);
    }

    @Override
    public Team findTeamByTeamId(String teamId) {
        return teamRepository.findTeamByTeamId(teamId);
    }

    @Override
    public Team saveTeamByTeacher(String teacherId, Team team) {
        if (null == team.getTeamName() || team.getTeamName().equals("")) {
            throw new ParamIncorrectException("班级不能为空", team);
        }
        team.setTeacherId(teacherId);
        team.setTeamId(UUIDUtil.generatedTeamId());
        team.setTeamVolume(0);
        team.setCreatedTime(new Date());
        return teamRepository.save(team);

    }

    @Override
    public List<Team> findTeamsByTeacherId(String teacherId) {
        return teamRepository.findTeamsByTeacherId(teacherId);
    }

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team getTeamInfo(String teamId) {
        Team team = teamRepository.findTeamByTeamId(teamId);
        if (team == null) {
            throw new ResultNotFoundException("teamId不存在", new Team());
        }
        return team;
    }

    @Override
    public int getTeamVolume(Team team) {
        return findTeamByTeamId(team.getTeamId()).getTeamVolume();
    }

    @Override
    public int getTeamsVolume(List<Team> teamList) {
        int sum = 0;
        for (Team team : teamList) {
            sum += team.getTeamVolume();
        }
        return sum;
    }

    @Override
    public List<Team> saveTeacherHomework(List<Team> team, TeacherHomework teacherHomework) {
        for (Team t : team) {
            Team s = teamRepository.findTeamByTeamId(t.getTeamId());
            s.getTeacherHomeworkList().add(teacherHomework);
            teamRepository.saveAndFlush(s);
        }
        return team;
    }

    @Override
    public List<Team> findTeamsByTeams(List<Team> teams) {
        List<Team> result = new ArrayList<>();
        for (Team team : teams) {
            result.add(teamRepository.findTeamByTeamId(team.getTeamId()));
        }
        return result;
    }


}
