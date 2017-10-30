package com.hanlinbode.hlbd.composbean;

import com.hanlinbode.hlbd.bean.Teacher;

public class TeacherAndToken {
    private Teacher teacher;
    private Token token;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public TeacherAndToken() {
    }

    public TeacherAndToken(Teacher teacher, Token token) {
        this.teacher = teacher;
        this.token = token;
    }
}
