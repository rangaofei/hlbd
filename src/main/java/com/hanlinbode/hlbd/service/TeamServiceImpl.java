package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.dao.TeamRepository;
import com.hanlinbode.hlbd.service.TeamService;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
