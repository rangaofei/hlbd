package com.hanlinbode.hlbd.bean;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UID;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String crteatedTime;
}
