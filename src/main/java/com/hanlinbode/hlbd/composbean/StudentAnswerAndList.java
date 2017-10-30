package com.hanlinbode.hlbd.composbean;

import com.hanlinbode.hlbd.bean.StudentAnswer;
import com.hanlinbode.hlbd.bean.StudentAnswerList;

import java.util.List;

public class StudentAnswerAndList {

    private StudentAnswer answer;

    private List<StudentAnswerList> answerList;

    public StudentAnswerAndList(StudentAnswer answer, List<StudentAnswerList> answerList) {
        this.answer = answer;
        this.answerList = answerList;
    }

    public StudentAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(StudentAnswer answer) {
        this.answer = answer;
    }

    public List<StudentAnswerList> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<StudentAnswerList> answerList) {
        this.answerList = answerList;
    }
}
