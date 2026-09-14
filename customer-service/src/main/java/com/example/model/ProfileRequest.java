package com.example.model;

public class ProfileRequest {

    private String customerId;

    public ProfileRequest() {
    }

    public ProfileRequest(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}