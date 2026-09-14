package com.example.model;

public class PreAuthResponse {

    private String preAuthToken;
    private String message;

    public PreAuthResponse() {
    }

    public PreAuthResponse(String preAuthToken, String message) {
        this.preAuthToken = preAuthToken;
        this.message = message;
    }

    public String getPreAuthToken() {
        return preAuthToken;
    }

    public void setPreAuthToken(String preAuthToken) {
        this.preAuthToken = preAuthToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
