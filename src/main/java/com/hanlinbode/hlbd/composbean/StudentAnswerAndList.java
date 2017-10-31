package com.hanlinbode.hlbd.composbean;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.StudentAnswerQuestion;

import java.util.List;

public class StudentAnswerAndList {

    private StudentAnswer answer;

    private List<StudentAnswerQuestion> answerList;

    public StudentAnswerAndList(StudentAnswer answer, List<StudentAnswerQuestion> answerList) {
        this.answer = answer;
        this.answerList = answerList;
    }

    public StudentAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(StudentAnswer answer) {
        this.answer = answer;
    }

    public List<StudentAnswerQuestion> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<StudentAnswerQuestion> answerList) {
        this.answerList = answerList;
    }
}
