package com.hanlinbode.hlbd.bean;

import javax.persistence.*;

@Entity
public class HomeWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UID;
    @Column(nullable = false)
    private int type;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int classId;
    @Column(nullable = false)
    private String crteatedTime;
}
