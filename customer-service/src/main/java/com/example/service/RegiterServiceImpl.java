package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.CustomerCredentialDAO;
import com.example.dao.LinkedAccountDAO;
import com.example.dao.RegiterDAO;
import com.example.model.CustomerCredential;
import com.example.model.LinkedAccount;
import com.example.model.Registration;

@Service
public class RegiterServiceImpl implements RegiterService {

    private final RegiterDAO regiterDAO;
    private final CustomerCredentialDAO credentialDAO;
    private final LinkedAccountDAO linkedAccountDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegiterServiceImpl(RegiterDAO regiterDAO,
                               CustomerCredentialDAO credentialDAO,
                               LinkedAccountDAO linkedAccountDAO,
                               PasswordEncoder passwordEncoder) {
        this.regiterDAO = regiterDAO;
        this.credentialDAO = credentialDAO;
        this.linkedAccountDAO = linkedAccountDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Registration registerCustomer(Registration registration) {
        String customerNo = "CUST" + System.currentTimeMillis();
        registration.setCustomerNo(customerNo);
        registration.setKycStatus("PENDING");
        registration.setStatus("ACTIVE");

        // 1. customer
        Long customerId = regiterDAO.saveRegistration(registration);
        registration.setId(customerId);

        // 2. customer_credential
        CustomerCredential credential = new CustomerCredential();
        credential.setCustomerId(customerId);
        credential.setUsername(registration.getUsername());
        credential.setPasswordHash(passwordEncoder.encode(registration.getPassword()));
        credential.setMpinHash(passwordEncoder.encode(registration.getMpin()));
        credentialDAO.saveCredential(credential);

        // 3. linked_account
        LinkedAccount account = new LinkedAccount();
        account.setCustomerId(customerId);
        account.setAccountNumber(registration.getAccountNumber());
        account.setIfsc(registration.getIfsc());
        account.setAccountType("SAVINGS");
        account.setNickname("Primary Savings");
        account.setPrimary(true);
        linkedAccountDAO.saveAccount(account);

        return registration;
    }
}