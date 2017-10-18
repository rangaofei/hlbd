package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.TeacherClass;

import java.util.List;

public interface TeacherClassDao {
    TeacherClass createClass(TeacherClass teacherClass);

    List<TeacherClass> getTeacherclasses(int teacherId);

}
