package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.composbean.StudentAndToken;
import com.hanlinbode.hlbd.composbean.Token;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService extends TokenService {

    Student findStudentByPhone(String phone);

    Student saveStudent(Student student);

    Student findStudentByStudentId(String studentId);

    Team joinTeam(String studentId, String teamId);

    StudentAndToken registerStudent(Student student);

    StudentAndToken loginStudent(Student student);

    List<Student> findStudentsByTeam(Team team);

    List<Student> findStudentsByTeams(List<Team> teams);
}
