package com.example.a1.campr;

public class Pet {
    private String name;
    private String gender;
    private String info;
    private String id;
    private String picUrl;
    private String listerId;

    public Pet() {}

    public Pet(String name, String gender, String info, String id, String picUrl, String listerId) {
        this.name = name;
        this.gender = gender;
        this.info = info;
        this.id = id;
        this.picUrl = picUrl;
        this.listerId = listerId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) { this.gender = gender; }

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) { this.info = info; }

    public String getId() {
        return id;
    }
    public void setId(String id) { this.id = id; }

    public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) { this.picUrl = picUrl; }

    public String getListerId() {
        return listerId;
    }
    public void setListerId(String listerId) { this.listerId = listerId; }
}
