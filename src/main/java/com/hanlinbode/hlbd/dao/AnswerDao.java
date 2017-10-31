package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.TeacherHomeWork;
import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;

import java.util.List;

public interface AnswerDao {
    StudentAnswer saveAnswer(TeacherHomeWork teacherHomeWork, Student student);

    StudentAnswer saveAnserByTeam(TeacherHomeWork teacherHomeWork, Team team);

    List<StudentAnswer> findAnswerByTeacherHomeWorkAndStudent(TeacherHomeWork teacherHomeWork, Student student);

    StudentAnswer findAnswerById(String answerId);

    StudentAnswer updateAnswer(StudentAnswer answer);


}
