package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;

import java.util.List;

public interface HomeworkQuestionService {
    List<TeacherHomeworkQuestion> findQuestionsByHomeworkId(String homeworkId);

    List<TeacherHomeworkQuestion> updateCommitState(String homeworkQuestionId);

    List<TeacherHomeworkQuestion> setQuestionHomeworkId(List<TeacherHomeworkQuestion> list,String homeworkId);

    TeacherHomeworkQuestion findTeacherHomeworkQuestionById(int homeworkQuestionId);

    TeacherHomeworkQuestion saveTeacherHomeworkQuestion(TeacherHomeworkQuestion question);

    boolean waitCorrect(String homeworkId);

    float calculateCorrectRate(String homeworkId);
}
