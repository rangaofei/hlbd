package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Teacher;

import java.util.List;

public interface TeacherDao {
    List<Teacher> findTeachers(String phone);

    Teacher saveTeacher(Teacher teacher);
}
