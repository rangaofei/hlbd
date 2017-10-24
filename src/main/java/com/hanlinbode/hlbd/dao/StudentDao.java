package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.responsebean.Token;

import java.util.List;

public interface StudentDao {
    Student saveStudent(Student student);

    Student findStudentByPhone(String phone);

    List<Student> findStudentsByName(String name);

    Token generateStudentToken(Student student);

    Student findStudentById(int id);

    Student findStudentByStudentId(String studentId);

    Student joinTeams(String studentId, String teamId);
}
