package com.anthonymwangi.kibu_rahisi.model;

public class OfficeLayoutModel {
    private String name, description, status, capacity;

    public OfficeLayoutModel() {

    }

    public OfficeLayoutModel(String name, String description, String status, String capacity) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.capacity = capacity;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
