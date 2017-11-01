package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;

import java.util.List;

public interface AnswerDao {
    StudentAnswer saveAnswer(TeacherHomework teacherHomework, Student student);

    StudentAnswer saveAnserByTeam(TeacherHomework teacherHomework, Team team);

    List<StudentAnswer> findAnswerByTeamAndStudent(String teamId, String studentId);

    StudentAnswer findAnswerById(String answerId);

    StudentAnswer updateAnswer(StudentAnswer answer);

    StudentAnswer calucateCorrectRate(String answerId);


}
