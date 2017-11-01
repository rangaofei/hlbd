package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.StudentAnswerQuestion;

import java.util.List;

public interface AnswerQuestionDao {
    void commitAnswer(List<StudentAnswerQuestion> questionList, StudentAnswer studentAnswer);

    List<StudentAnswerQuestion> findAnswerQuestionByAnswerId(String answerId);
}
