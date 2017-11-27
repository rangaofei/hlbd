package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;
import com.hanlinbode.hlbd.composbean.StudentCostTime;

import java.util.List;

public interface AnswerService {
    StudentAnswer saveAnswer(TeacherHomework teacherHomework, Student student);

    List<StudentAnswer> saveAnswerByTeam(TeacherHomework teacherHomework, Team team);

    List<StudentAnswer> saveAnswerByTeams(TeacherHomework teacherHomework, List<Team> teams);

    List<StudentAnswer> saveAnswerByStudents(TeacherHomework teacherHomework, List<Student> students);

    List<StudentAnswer> findAnswerByTeamAndStudent(String teamId, String studentId);

    StudentAnswer findAnswerById(String answerId);

    StudentAnswer updateAnswer(StudentAnswer answer);

    StudentAnswer calucateCorrectRate(String answerId);

    StudentCostTime getStudentCostTime(String studentId);
}
