package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.HomeWork;
import org.springframework.beans.factory.annotation.Autowired;

public class HomeWorkDaoImpl implements HomeWorkDao {
    @Autowired
    private HomeWorkRepository homeWorkRepository;

    @Override
    public HomeWork createHomeWork(HomeWork homeWork) {
        homeWorkRepository.save(homeWork);
        return homeWork;
    }
}
