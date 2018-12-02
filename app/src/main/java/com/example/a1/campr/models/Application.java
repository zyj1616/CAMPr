package com.example.a1.campr.models;

public class Application {

    private String applicationId;
    private String listerId;
    private String adopterId;
    private String petId;
    private boolean approval;
    private String message;

    public Application(String listerId, String adopterId, String petId, boolean approval, String message, boolean currentContact) {
        this.listerId = listerId;
        this.adopterId = adopterId;
        this.petId = petId;
        this.approval = approval;
        this.message = message;
        this.currentContact = currentContact;
    }
    public Application(){

    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isCurrentContact() {
        return currentContact;
    }

    public void setCurrentContact(boolean currentContact) {
        this.currentContact = currentContact;
    }

    private boolean currentContact;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getListerId() {
        return listerId;
    }

    public void setListerId(String listerId) {
        this.listerId = listerId;
    }

    public String getAdopterId() {
        return adopterId;
    }

    public void setAdopterId(String adopterId) {
        this.adopterId = adopterId;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }


}

