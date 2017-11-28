package com.hanlinbode.hlbd.composbean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class StudentRate implements Serializable {
    private Map<String, Float> self;
    private Map<String, Float> average;

    public Map<String, Float> getSelf() {
        return self;
    }

    public void setSelf(Map<String, Float> self) {
        this.self = self;
    }

    public Map<String, Float> getAverage() {
        return average;
    }

    public void setAverage(Map<String, Float> average) {
        this.average = average;
    }
}
