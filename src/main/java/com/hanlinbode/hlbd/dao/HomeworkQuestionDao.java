package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;

import java.util.List;

public interface HomeworkQuestionDao {
    List<TeacherHomeworkQuestion> findQuestionsByHomeworkId(String homeworkId);

    List<TeacherHomeworkQuestion> updateCommitState(String homeworkQuestionId);

    TeacherHomeworkQuestion findTeacherHomeworkQuestionById(int homeworkQuestionId);

    TeacherHomeworkQuestion saveTeacherHomeworkQuestion(TeacherHomeworkQuestion question);
}
