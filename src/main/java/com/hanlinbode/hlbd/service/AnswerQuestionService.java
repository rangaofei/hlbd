package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.*;
import com.hanlinbode.hlbd.enums.AnswerState;

import java.util.List;

public interface AnswerQuestionService {
    void commitAnswer(List<StudentAnswerQuestion> questionList, StudentAnswer studentAnswer);

    void commitAnswerQuestion(List<StudentAnswerQuestion> questionList, StudentAnswer studentAnswer);

    List<StudentAnswerQuestion> findAnswerQuestionByAnswerId(String answerId);

    List<StudentAnswerQuestion> findAnserQuesitonByHomeworkQuestionId(int id);

    StudentAnswerQuestion findAnswerQuestionById(int id);

    float calculateStudentAnswerCorrectRate(String answerId);

    float calculateHomeworkQuestionCorrectRate(int homeworkQuestionId);

    AnswerState studentAnswerState(String AnswerId);

    AnswerState teacherHomeworkState(int homeworkquestionId);

    void createAnswerQuestion(List<TeacherHomeworkQuestion> teacherHomeworkQuestionList, List<StudentAnswer> studentAnswerList);
}
