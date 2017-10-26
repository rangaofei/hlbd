package com.hanlinbode.hlbd.bean;

import java.util.List;

public class CreateHomeWork {
    private HomeWork homeWork;
    private List<Team> teams;

    public HomeWork getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(HomeWork homeWork) {
        this.homeWork = homeWork;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
