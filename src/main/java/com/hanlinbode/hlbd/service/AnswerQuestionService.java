package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.bean.*;

import java.util.List;

public interface AnswerQuestionService {
    void commitAnswer(List<StudentAnswerQuestion> questionList, StudentAnswer studentAnswer);

    List<StudentAnswerQuestion> findAnswerQuestionByAnswerId(String answerId);

    List<StudentAnswerQuestion> findAnserQuesitonByHomeworkQuestionId(int id);

    float calculateStudentAnswerCorrectRate(String answerId);

    float calculateHomeworkQuestionCorrectRate(int homeworkQuestionId);

    boolean waitCorrect(int homeworkQuestionId);

    void createAnswerQuestion(List<TeacherHomeworkQuestion> teacherHomeworkQuestionList, List<StudentAnswer> studentAnswerList);
}
