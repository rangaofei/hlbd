package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.TeacherSubject;

import javax.print.DocFlavor;
import java.util.List;

public interface TeacherSubjectService {

    List<TeacherSubject> findSubjectsByTeacherId(String teacherId);

    List<TeacherSubject> saveTeacherSubject(String teacherId, List<TeacherSubject> teacherSubject);
}
