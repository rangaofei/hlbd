package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import com.hanlinbode.hlbd.bean.Team;

import java.util.List;

public interface HomeWorkService {
    TeacherHomework createHomeWork(String teacherId, TeacherHomework teacherHomework,List<Team> teams,int totaolStudent);

    List<TeacherHomework> findHomeWorkByTeacherId(String teacherId);

    TeacherHomework findHomeWorkByHomeWorkId(String homeworkId);

    TeacherHomework updateTeacherHomeWork(TeacherHomework teacherHomework);

    TeacherHomework updateCommitCount(String homeworkId);

    float calculateDifficult(List<TeacherHomeworkQuestion> questionList);

    TeacherHomework setHomeworkTotalStudent(int count, TeacherHomework teacherHomework);

    TeacherHomework saveTeacherHomework(TeacherHomework teacherHomework);
}
