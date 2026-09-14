package com.example.service;

import com.example.model.LoginRequest;
import com.example.model.LoginResponse;
import com.example.model.PreAuthResponse;
import com.example.model.VerifyOtpRequest;

public interface AuthService {

    PreAuthResponse login(LoginRequest loginRequest);

    LoginResponse verifyOtp(VerifyOtpRequest verifyOtpRequest);
}
