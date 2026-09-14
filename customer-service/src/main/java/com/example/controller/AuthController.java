package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.LoginRequest;
import com.example.model.LoginResponse;
import com.example.model.PreAuthResponse;
import com.example.model.VerifyOtpRequest;
import com.example.service.AuthService;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/auth/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            PreAuthResponse PreAuthResponse = authService.login(loginRequest);
            return ResponseEntity.status(HttpStatus.OK).body(PreAuthResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                java.util.Map.of("error", e.getMessage())
            );
        }
    }

    @PostMapping(value = "/auth/verify-otp", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
        try {
            LoginResponse loginResponse = authService.verifyOtp(verifyOtpRequest);
            return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                java.util.Map.of("error", e.getMessage())
            );
        }
    }
}
