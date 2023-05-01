package com.anthonymwangi.kibu_rahisi.model;

public class Classes {
    String className, startTime, endTime;

    public Classes() {
    }

    public Classes(String className, String startTime, String endTime) {
        this.className = className;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
