package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.HomeWork;

import java.util.List;

public interface HomeWorkDao {
    HomeWork createHomeWork(String teacherId, HomeWork homeWork);

    List<HomeWork> findHomeWorkByTeacherId(String teacherId);
}
