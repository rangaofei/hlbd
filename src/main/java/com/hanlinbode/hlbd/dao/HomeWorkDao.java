package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.HomeWork;
import com.hanlinbode.hlbd.bean.Team;

import java.util.List;

public interface HomeWorkDao {
    HomeWork createHomeWork(String teacherId, HomeWork homeWork, List<Team> teams);

    List<HomeWork> findHomeWorkByTeacherId(String teacherId);
}
