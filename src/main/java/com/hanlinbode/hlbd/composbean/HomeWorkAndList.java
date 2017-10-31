package com.hanlinbode.hlbd.composbean;

import com.hanlinbode.hlbd.bean.TeacherHomework;
import com.hanlinbode.hlbd.bean.TeacherHomeworkQuestion;

import java.util.List;

public class HomeWorkAndList {
    private TeacherHomework teacherHomework;
    private List<TeacherHomeworkQuestion> teacherHomeworkQuestion;

    public TeacherHomework getTeacherHomework() {
        return teacherHomework;
    }

    public void setTeacherHomework(TeacherHomework teacherHomework) {
        this.teacherHomework = teacherHomework;
    }

    public List<TeacherHomeworkQuestion> getTeacherHomeworkQuestion() {
        return teacherHomeworkQuestion;
    }

    public void setTeacherHomeworkQuestion(List<TeacherHomeworkQuestion> teacherHomeworkQuestion) {
        this.teacherHomeworkQuestion = teacherHomeworkQuestion;
    }
}
