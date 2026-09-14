
package com.example.model;

public class VerifyOtpRequest {

    private String preAuthToken;
    private String otp;

    public VerifyOtpRequest() {
    }

    public VerifyOtpRequest(String preAuthToken, String otp) {
        this.preAuthToken = preAuthToken;
        this.otp = otp;
    }

    public String getPreAuthToken() {
        return preAuthToken;
    }

    public void setPreAuthToken(String preAuthToken) {
        this.preAuthToken = preAuthToken;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
