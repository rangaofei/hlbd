package com.hanlinbode.hlbd.bean;

import javax.persistence.*;

@Entity
@Table(name = "l_question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String answer;
    private int bookNodeId;
    private int difficult;
    private String questionAnalyze;
    private int questionTypeId;
    private String stem;
}
