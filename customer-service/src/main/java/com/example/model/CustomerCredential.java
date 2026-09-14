package com.example.model;

public class CustomerCredential {
    private Long id;
    private Long customerId;
    private String username;
    private String passwordHash;
    private String mpinHash;

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
    public String getUsername() {
    	return username; 
    	}
    public void setUsername(String username) {
    	this.username = username;
    	}
    public String getPasswordHash() {
    	return passwordHash;
    	}
    public void setPasswordHash(String passwordHash) {
    	this.passwordHash = passwordHash; 
    	}
    public String getMpinHash() {
    	return mpinHash;
    	}
    public void setMpinHash(String mpinHash) {
    	this.mpinHash = mpinHash; 
    	}
}