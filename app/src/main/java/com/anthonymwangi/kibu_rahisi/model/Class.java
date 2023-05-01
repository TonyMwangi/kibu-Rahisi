package com.anthonymwangi.kibu_rahisi.model;

public class Class {
    private String className, programme, yearOfStudy, classCode, startTime, endTime;

    public Class() {
    }

    public Class(String className, String programme, String yearOfStudy, String classCode, String startTime, String endTime) {
        this.className = className;
        this.programme = programme;
        this.yearOfStudy = yearOfStudy;
        this.classCode = classCode;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
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
