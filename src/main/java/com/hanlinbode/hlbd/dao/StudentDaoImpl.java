package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.bean.Token;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Student saveStudent(Student student) {
        student.setCrteatedTime(new Date());
        student.setStudentId(UUIDUtil.generateId());
        return studentRepository.save(student);

    }

    @Override
    public Student findStudentByPhone(String phone) {
        return studentRepository.findStudentByPhone(phone);
    }


    @Override
    public List<Student> findStudentsByName(String name) {
        return studentRepository.findStudentsByName(name);
    }

    @Override
    public Token generateStudentToken(Student student) {
        return new Token("SSS" + student.getPhone());
    }

    @Override
    public Student findStudentById(int id) {
        return studentRepository.findStudentById(id);
    }

    @Override
    public Student findStudentByStudentId(String studentId) {
        return studentRepository.findStudentByStudentId(studentId);
    }

    @Override
    public Student joinTeams(String studentId, String teamId) {
        Student student = studentRepository.findStudentByStudentId(studentId);
        Team team = teamRepository.findTeamByTeamId(teamId);
        if (student.getTeamList().contains(team)) {
            return null;
        }
        student.getTeamList().add(team);
        return studentRepository.save(student);
    }
}