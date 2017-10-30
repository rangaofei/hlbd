package com.hanlinbode.hlbd.composbean;

import com.hanlinbode.hlbd.bean.Student;

public class StudentAndToken {
    private Student student;
    private Token token;

    public StudentAndToken() {
    }

    public StudentAndToken(Student student, Token token) {
        this.student = student;
        this.token = token;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
