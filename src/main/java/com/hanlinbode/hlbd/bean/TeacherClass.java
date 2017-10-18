package com.hanlinbode.hlbd.bean;

import javax.persistence.*;

@Entity
public class TeacherClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;
    @Column(nullable = false)
    private String className;
    @Column(nullable = false)
    private String classIntroduction;
    @Column(nullable = false)
    private int teacherId;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassIntroduction() {
        return classIntroduction;
    }

    public void setClassIntroduction(String classIntroduction) {
        this.classIntroduction = classIntroduction;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "TeacherClass{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", classIntroduction='" + classIntroduction + '\'' +
                ", teacherId=" + teacherId +
                '}';
    }
}
