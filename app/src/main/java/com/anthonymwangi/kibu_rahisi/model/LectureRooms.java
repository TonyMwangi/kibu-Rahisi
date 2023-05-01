package com.anthonymwangi.kibu_rahisi.model;

public class LectureRooms {
    private String name;
    private String status;
    private String capacity;

    public LectureRooms() {
    }

    public LectureRooms(String name, String status, String capacity) {
        this.name = name;
        this.status = status;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
