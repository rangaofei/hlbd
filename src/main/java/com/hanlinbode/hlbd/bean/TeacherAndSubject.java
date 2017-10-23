package com.hanlinbode.hlbd.bean;

import javax.security.auth.Subject;
import java.util.List;

public class TeacherAndSubject {
    private Teacher teacher;
    private List<TeacherSubject> teacherSubjects;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<TeacherSubject> getTeacherSubjects() {
        return teacherSubjects;
    }

    public void setTeacherSubjects(List<TeacherSubject> teacherSubjects) {
        this.teacherSubjects = teacherSubjects;
    }
}
