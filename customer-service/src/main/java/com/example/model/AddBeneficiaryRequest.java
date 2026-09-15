package com.example.model;

public class AddBeneficiaryRequest {
    private String beneName;
    private String accountNumber;
    private String ifsc;

    
    public String getBeneName() { 
    	return beneName; 
    }
    public void setBeneName(String beneName)  {
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
}