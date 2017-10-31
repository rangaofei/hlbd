package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.Team;

import java.util.List;

public interface HomeWorkDao {
    TeacherHomework createHomeWork(String teacherId, TeacherHomework teacherHomework, List<Team> teams);

    List<TeacherHomework> findHomeWorkByTeacherId(String teacherId);

    TeacherHomework findHomeWorkByHomeWorkId(String homeworkId);

    TeacherHomework updateTeacherHomeWork(TeacherHomework teacherHomework);
}
