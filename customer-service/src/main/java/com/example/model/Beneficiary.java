package com.example.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Beneficiary {
    private Long id;
    @JsonIgnore
   private Long customerId;
    private String beneName;
    private String accountNumber;
    private String ifsc;
    private String status;          // PENDING, ACTIVE
    private LocalDateTime coolingPeriodEnds;

    // getters and setters
    public Long getId() { 
    	return id; 
    	}
    public void setId(Long id) {
    	this.id = id; 
    	}

   public Long getCustomerId() {
	   return customerId; 
	   }
   public void setCustomerId(Long customerId) {
	   this.customerId = customerId; 
	   }

    public String getBeneName() { 
    	return beneName; 
    	}
    public void setBeneName(String beneName) { 
    	this.beneName = beneName;
    	}

    public String getAccountNumber() {
    	return accountNumber; 
    	}
    public void setAccountNumber(String accountNumber) {
    	this.accountNumber = accountNumber; 
    	}

    public String getIfsc() { 
    	return ifsc;
    	}
    public void setIfsc(String ifsc) { 
    	this.ifsc = ifsc;
    	}

    public String getStatus() {
    	return status; 
    	}
    public void setStatus(String status) {
    	this.status = status; 
    	}

    public LocalDateTime getCoolingPeriodEnds() {
    	return coolingPeriodEnds; 
    	}
    public void setCoolingPeriodEnds(LocalDateTime coolingPeriodEnds) {
    	this.coolingPeriodEnds = coolingPeriodEnds;
    	}
}