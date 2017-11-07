package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;
import com.hanlinbode.hlbd.enums.AnswerState;

import java.util.List;

public interface HomeworkQuestionService {
    List<TeacherHomeworkQuestion> findQuestionsByHomeworkId(String homeworkId);

    List<TeacherHomeworkQuestion> updateCommitState(String homeworkQuestionId);

    List<TeacherHomeworkQuestion> setQuestionHomeworkId(List<TeacherHomeworkQuestion> list, String homeworkId);

    TeacherHomeworkQuestion findTeacherHomeworkQuestionById(int homeworkQuestionId);

    TeacherHomeworkQuestion saveTeacherHomeworkQuestion(TeacherHomeworkQuestion question);

    AnswerState homeworkState(String homeworkId);

    float calculateCorrectRate(String homeworkId);
}
