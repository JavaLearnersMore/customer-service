package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Registration;
import com.example.model.RegistrationResponse;
import com.example.service.RegiterService;

@RestController
@RequestMapping("/api/v1")
public class RegisterController {

    private final RegiterService regiterService;

    @Autowired
    public RegisterController(RegiterService regiterService) {
        this.regiterService = regiterService;
    }

 
            @PostMapping(value = "/customers/register", consumes = "application/json", produces = "application/json")
            public ResponseEntity<?> registerCustomer(@RequestBody Registration registration) {
                try {
                    Registration saved = regiterService.registerCustomer(registration);
                    return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse(saved));
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        java.util.Map.of("error", e.getMessage())
                    );
        }
    }
}