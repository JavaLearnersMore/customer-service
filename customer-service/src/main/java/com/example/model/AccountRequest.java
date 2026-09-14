package com.example.model;

public class AccountRequest {

    private String customerId;

    public AccountRequest() {
    }

    public AccountRequest(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}