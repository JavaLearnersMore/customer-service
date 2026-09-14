package com.example.service;

import com.example.dao.CustomerCredentialDAO;
import com.example.dao.CustomerCredentialDAOImpl;
import com.example.dao.CustomerDAO;
import com.example.model.ChangeMpinRequest;
import com.example.model.CustomerCredential;
import com.example.model.CustomerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final CustomerDAO customerDAO;
    private final CustomerCredentialDAO customerCredentialDAO;
    

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public ProfileServiceImpl(CustomerDAO customerDAO, CustomerCredentialDAO customerCredentialDAO) {
        this.customerDAO = customerDAO;
        this.customerCredentialDAO = customerCredentialDAO;
    }

//
//    @Override
//    public void changeMpin(Long customerId, ChangeMpinRequest request) {
//        if (customerId == null) {
//            throw new IllegalArgumentException("customerId is required");
//        }
//        String storedMpinHash = customerCredentialDAO.findMpinHashByCustomerId(customerId);
//
//        if (storedMpinHash == null) {
//            throw new IllegalArgumentException("No MPIN set for customer: " + customerId);
//        }
//        if (!passwordEncoder.matches(request.getCurrentMpin(), storedMpinHash)) {
//            throw new IllegalArgumentException("Current MPIN is incorrect");
//        }
//
//        String newMpinHash = passwordEncoder.encode(request.getNewMpin());
//        int updated = customerCredentialDAO.updateMpinHash(customerId, newMpinHash);
//        if (updated == 0) {
//            throw new IllegalStateException("Failed to update MPIN");
//        }
//    }
    @Override
    public void changeMpin(ChangeMpinRequest request) {
    	if (request.getCustomerId() == null) {
            throw new IllegalArgumentException("customerId is required");
        }

        Long customerId;
        try {
            customerId = Long.valueOf(request.getCustomerId());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("customerId must be a valid number");
        }

        CustomerCredential credential = customerCredentialDAO.findByCustomerId(customerId);
        if (credential == null) {
            throw new IllegalArgumentException("No account found for customer: " + customerId);
        }

        String storedMpinHash = credential.getMpinHash();
        if (storedMpinHash == null) {
            throw new IllegalArgumentException("No MPIN set for customer: " + customerId);
        }

        if (!passwordEncoder.matches(request.getCurrentMpin(), storedMpinHash)) {
            throw new IllegalArgumentException("Current MPIN is incorrect");
        }

        String newMpinHash = passwordEncoder.encode(request.getNewMpin());
        int updated = customerCredentialDAO.updateMpinHash(customerId, newMpinHash);
        if (updated == 0) {
            throw new IllegalStateException("Failed to update MPIN");
        }
    }
   
	@Override
	public CustomerProfile getProfile(Long customerId) {
		if (customerId == null ) {
            throw new IllegalArgumentException("customerId is required");
        }
        CustomerProfile profile = customerDAO.findById(Long.valueOf(customerId));
        if (profile == null) {
            throw new IllegalArgumentException("Customer not found: " + customerId);
        }
        return profile;
	}
	
	public static void main(String[] args) {
		CustomerCredentialDAO dao = new CustomerCredentialDAOImpl();
		CustomerCredential encodedHashMpin = dao.decodeHashMpin("5003");
		BCryptPasswordEncoder passwordEncodernew = new BCryptPasswordEncoder();
		if (!passwordEncodernew.matches("1234", encodedHashMpin.getMpinHash())) {
            throw new IllegalArgumentException("Invalid username or password");
        }else {
        	System.out.println("valid mpin provided");
        }
		
		
	}
}