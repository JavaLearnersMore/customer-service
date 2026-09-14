package com.example.model;

public class ChangeMpinRequest {

	private Long  customerId;
    private String currentMpin;
    private String newMpin;

    public ChangeMpinRequest() {
    }

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long  customerId) {
		this.customerId = customerId;
	}

	public String getCurrentMpin() {
		return currentMpin;
	}

	public void setCurrentMpin(String currentMpin) {
		this.currentMpin = currentMpin;
	}

	public String getNewMpin() {
		return newMpin;
	}

	public void setNewMpin(String newMpin) {
		this.newMpin = newMpin;
	}

    

	



}