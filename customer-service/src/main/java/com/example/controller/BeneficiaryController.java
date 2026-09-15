package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.AddBeneficiaryRequest;
import com.example.model.Beneficiary;
import com.example.service.BeneficiaryService;

@RestController
@RequestMapping("/api/v1/beneficiaries")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addBeneficiary(
            @RequestParam Long customerId, 
            @RequestBody AddBeneficiaryRequest request) {
        try {
            Beneficiary b = beneficiaryService.addBeneficiary(customerId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(b);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(java.util.Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> listBeneficiaries(@RequestParam Long customerId) {
        try {
            return ResponseEntity.ok(beneficiaryService.listBeneficiaries(customerId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(java.util.Map.of("error", e.getMessage()));
        }
    }
    
    
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteBeneficiary(@PathVariable Long id, @RequestParam Long customerId) {
        try {
            beneficiaryService.deleteBeneficiary(id, customerId);
            return ResponseEntity.ok(java.util.Map.of("message", "Beneficiary deleted successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(java.util.Map.of("error", e.getMessage()));
        }
    }
}