package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.composbean.TeacherAndToken;
import com.hanlinbode.hlbd.composbean.Token;

import java.util.List;

public interface TeacherDao {
    List<Teacher> findTeachers(String phone);

    Teacher saveTeacher(Teacher teacher);

    Teacher findTeacherByPhone(String phone);

    Token generateTeacherToken(Teacher teacher);

    List<Teacher> findTeachersByName(String name);

    Teacher findTeacherByTeacherId(String teacherId);

    TeacherAndToken registerTeacher(Teacher teacher);

}
