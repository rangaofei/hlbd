package com.hanlinbode.hlbd.bean;

import javax.persistence.*;

@Entity
@Table(name = "l_subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int stage;
    @Column(nullable = false)
    private int xkSubjectId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getXkSubjectId() {
        return xkSubjectId;
    }

    public void setXkSubjectId(int xkSubjectId) {
        this.xkSubjectId = xkSubjectId;
    }
}
