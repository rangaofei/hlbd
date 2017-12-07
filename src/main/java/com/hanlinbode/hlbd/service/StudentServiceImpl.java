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

    /**
     * 学生注册
     * 假如学生手机号码已存在，则会抛出用户手机号已被注册的异常
     *
     * @param student 学生信息
     * @return 学生注册信息（包含token）
     */
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

    /**
     * @param student
     * @return
     */
    @Override
    public StudentAndToken loginStudent(Student student) {
        Student s = studentRepository.findStudentByPhone(student.getPhone());
        if (null == s) {
            throw new ResultNotFoundException("用户未注册", new StudentAndToken(student, null));
        }
        if (!student.getPassword().equals(s.getPassword())) {
            throw new ParamIncorrectException("用户密码错误", new StudentAndToken(student, null));
        }
        return new StudentAndToken(s, generateToken(student.getPhone()));
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
