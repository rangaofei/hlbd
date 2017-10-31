package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.composbean.StudentAndToken;
import com.hanlinbode.hlbd.composbean.Token;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StudentDao {
    Student saveStudent(Student student);

    Student findStudentByPhone(String phone);

    List<Student> findStudentsByName(String name);

    Token generateStudentToken(Student student);

    Student findStudentById(int id);

    Student findStudentByStudentId(String studentId);

    Team joinTeam(String studentId, String teamId);

    StudentAndToken registerStudent(Student student);
}
