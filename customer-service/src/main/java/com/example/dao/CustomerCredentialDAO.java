package com.example.dao;

import com.example.model.CustomerCredential;

public interface CustomerCredentialDAO {

    int saveCredential(CustomerCredential credential);

    //String findMpinHashByCustomerId(Long customerId);
    
    int updateMpinHash(Long customerId, String newMpinHash);

    CustomerCredential findByUsername(String username);
    
    CustomerCredential  findByHashMpin(String hashMpin);
    
    CustomerCredential decodeHashMpin(String custid);
    
    CustomerCredential findByCustomerId(Long customerId);   // <-- new
    
}