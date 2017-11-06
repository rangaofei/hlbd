package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.composbean.StudentAndToken;
import com.hanlinbode.hlbd.composbean.Token;
import com.hanlinbode.hlbd.dao.StudentRepository;
import com.hanlinbode.hlbd.dao.TeamRepository;
import com.hanlinbode.hlbd.exception.ParamIncorrectException;
import com.hanlinbode.hlbd.exception.ResultAlreadyExistException;
import com.hanlinbode.hlbd.exception.ResultNotFoundException;
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
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
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
        if (null != studentRepository.findStudentByPhone(student.getPhone())) {
            throw new ResultAlreadyExistException("该用户已注册", new StudentAndToken(student, null));
        }
        student.setCreatedTime(new Date());
        student.setStudentId(UUIDUtil.generateId());
        studentRepository.saveAndFlush(student);
        Token token = generateToken(student.getPhone());
        return new StudentAndToken(student, token);
    }

    @Override
    public StudentAndToken loginStudent(Student student) {
        Student s = studentRepository.findStudentByPhone(student.getPhone());
        if (null == s) {
            throw new ResultNotFoundException("用户未注册", new StudentAndToken(student,null));
        }
        if (!student.getPassword().equals(s.getPassword())) {
            throw new ParamIncorrectException("用户密码错误",  new StudentAndToken(student,null));
        }
        return new StudentAndToken(s, Token.generateToken(s.getPhone()));
    }

    @Override
    public List<Student> findStudentsByTeam(Team team) {
        return null;
    }

    @Override
    public List<Student> findStudentsByTeams(List<Team> teams) {
        return null;
    }

    @Override
    public Token generateToken(String src) {
        return Token.generateToken("SSS" + src);
    }
}