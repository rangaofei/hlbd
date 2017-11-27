package com.hanlinbode.hlbd.composbean;

import java.io.Serializable;
import java.util.Map;

public class StudentCostTime implements Serializable{
    private int totalTime;
    private Map<String, Integer> perSubject;

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public Map<String, Integer> getPerSubject() {
        return perSubject;
    }

    public void setPerSubject(Map<String, Integer> perSubject) {
        this.perSubject = perSubject;
    }
}
