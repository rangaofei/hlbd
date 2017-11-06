package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.composbean.StudentAndToken;
import com.hanlinbode.hlbd.composbean.Token;
import com.hanlinbode.hlbd.dao.StudentRepository;
import com.hanlinbode.hlbd.dao.TeamRepository;
import com.hanlinbode.hlbd.service.StudentService;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeamRepository teamRepository;


    @Override
    public Student findStudentByPhone(String phone) {
        return studentRepository.findStudentByPhone(phone);
    }


    @Override
    public Student findStudentByStudentId(String studentId) {
        return studentRepository.findStudentByStudentId(studentId);
    }

    @Override
    public Team joinTeam(String studentId, String teamId) {
        Student student = studentRepository.findStudentByStudentId(studentId);
        Team team = teamRepository.findTeamByTeamId(teamId);
        if (null == student || student.getTeamList().contains(team)) {
            return null;
        }
        student.getTeamList().add(team);
        studentRepository.save(student);
        team.setTeamVolume(team.getStudents().size());
        teamRepository.save(team);
        return team;
    }

    @Override
    public StudentAndToken registerStudent(Student student) {
        student.setCreatedTime(new Date());
        student.setStudentId(UUIDUtil.generateId());
        studentRepository.saveAndFlush(student);
        Token token = generateToken(student.getPhone());
        return new StudentAndToken(student, token);
    }

    @Override
    public Token generateToken(String src) {
        return Token.generateToken("SSS" + src);
    }
}
