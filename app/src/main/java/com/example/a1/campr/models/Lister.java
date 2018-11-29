package com.example.a1.campr.models;

import java.util.ArrayList;

public class Lister {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String city;
    private String state;
    private String picUrl;
    private ArrayList<String> listedPets;

    public Lister() {}

    public Lister(String firstname, String lastname, String email, String phoneNumber, String city, String state, String picUrl) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.picUrl = picUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public ArrayList<String> getListedPets() {
        return listedPets;
    }

    public void setListedPets(ArrayList<String> listedPets) {
        this.listedPets = listedPets;
    }
}
