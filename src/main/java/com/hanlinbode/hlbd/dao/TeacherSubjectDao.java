package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.TeacherSubject;

import javax.print.DocFlavor;
import java.util.List;

public interface TeacherSubjectDao {

    List<TeacherSubject> findSubjectsByTeacherId(String teacherId);

    List<TeacherSubject> saveTeacherSubject(String teacherId, List<TeacherSubject> teacherSubject);
}
