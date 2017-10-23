package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.Answer;
import com.hanlinbode.hlbd.bean.HomeWork;
import com.hanlinbode.hlbd.bean.Student;
import com.hanlinbode.hlbd.bean.Team;

public interface AnswerDao {
    Answer saveAnswer(HomeWork homeWork, Student student);

    Answer saveAnserByTeam(HomeWork homeWork, Team team);
}
