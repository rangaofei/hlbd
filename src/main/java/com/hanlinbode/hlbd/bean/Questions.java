package com.hanlinbode.hlbd.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UID;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int type;
    @Column(nullable = false)
    private String homeworkId;
    @Column(nullable = false)
    private String crteatedTime;
}
