package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Account;
import com.example.model.ChangeMpinRequest;
import com.example.model.CustomerProfile;
import com.example.service.AccountService;
import com.example.service.ProfileService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;
    private final ProfileService profileService;

    @Autowired
    public AccountController(AccountService accountService, ProfileService profileService){
        this.accountService = accountService;
        this.profileService = profileService;
    }
    
    @GetMapping(value = "/accounts", produces = "application/json")
    public ResponseEntity<?> getLinkedAccounts(@RequestParam Long customerId) {
        try {
            List<Account> accounts = accountService.getLinkedAccounts(customerId);
            return ResponseEntity.status(HttpStatus.OK).body(accounts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                java.util.Map.of("error", e.getMessage())
            );
        }
    } 
    @GetMapping(value = "/profile", produces = "application/json")
    public ResponseEntity<?> getProfile(@RequestParam Long customerId) {
        try {
            CustomerProfile profile = profileService.getProfile(customerId);
            return ResponseEntity.status(HttpStatus.OK).body(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                java.util.Map.of("error", e.getMessage())
            );
        }
    }

    @PutMapping(value = "/profile/mpin", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> changeMpin( @RequestBody ChangeMpinRequest request) {
        try {
            profileService.changeMpin(request);
            return ResponseEntity.status(HttpStatus.OK).body(
                java.util.Map.of("message", "MPIN changed successfully")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                java.util.Map.of("error", e.getMessage())
            );
        }
    }
}