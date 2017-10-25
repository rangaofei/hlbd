package com.hanlinbode.hlbd.bean;

import javax.persistence.*;

@Entity
@Table(name = "l_version")
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int subjectId;
    @Column(nullable = false)
    private int xkSubjectId;
    @Column(nullable = false)
    private int xkversionId;
}
