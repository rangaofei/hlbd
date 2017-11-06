package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.Teacher;
import com.hanlinbode.hlbd.composbean.TeacherAndToken;
import com.hanlinbode.hlbd.composbean.Token;

public interface TeacherService extends TokenService{
    Teacher checkTeacherExist(Teacher teacher);

    TeacherAndToken registerTeacher(Teacher teacher);

    Teacher findTeacherByTeacherId(String teacherId);

}
