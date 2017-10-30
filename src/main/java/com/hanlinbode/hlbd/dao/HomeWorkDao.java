package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.TeacherHomeWork;
import com.hanlinbode.hlbd.bean.Team;

import java.util.List;

public interface HomeWorkDao {
    TeacherHomeWork createHomeWork(String teacherId, TeacherHomeWork teacherHomeWork, List<Team> teams);

    List<TeacherHomeWork> findHomeWorkByTeacherId(String teacherId);

    TeacherHomeWork findHomeWorkByHomeWorkId(String homeworkId);

    TeacherHomeWork updateTeacherHomeWork(TeacherHomeWork teacherHomeWork);
}
