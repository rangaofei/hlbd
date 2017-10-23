package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamDaoImpl implements TeamDao {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeacherRepository teacherRepository;


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
    public Teacher saveTeamByTeacher(String teacherId, Team team) {
        Teacher teacher = teacherRepository.findTeacherByTeacherId(teacherId);
        team.setTeacher(teacher);
        team.setTeamId(UUIDUtil.generatedTeamId());
        teacher.getTeams().add(team);
        return teacherRepository.save(teacher);

    }

}
