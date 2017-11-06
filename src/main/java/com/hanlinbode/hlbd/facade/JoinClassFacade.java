package com.hanlinbode.hlbd.facade;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.exception.ResultAlreadyExistException;
import com.hanlinbode.hlbd.exception.ResultNotFoundException;
import com.hanlinbode.hlbd.service.StudentService;
import com.hanlinbode.hlbd.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class JoinClassFacade {

    private static final Logger logger = LoggerFactory.getLogger(JoinClassFacade.class);
    @Autowired
    private TeamService teamService;
    @Autowired
    private StudentService studentService;

    public Team JoinTeam(String studentId, String teamId) {
        Student student = studentService.findStudentByStudentId(studentId);
        Team team = teamService.findTeamByTeamId(teamId);
        logger.info("StudentId=" + studentId + ",teamId=" + teamId);
        if (student == null) {
            throw new ResultNotFoundException("学生ID不存在");
        }
        if (team == null) {
            throw new ResultNotFoundException("teamID不存在");
        }
        if (team.getStudents().contains(student)) {
            throw new ResultAlreadyExistException("已加入过该班级");
        }
        student.getTeamList().add(team);
        studentService.saveStudent(student);
        team.setTeamVolume(team.getStudents().size());
        return teamService.saveTeam(team);
    }
}
