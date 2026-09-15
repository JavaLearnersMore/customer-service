package com.example.dao;

import java.util.List;

import com.example.model.Beneficiary;

public interface BeneficiaryDAO {
    int saveBeneficiary(Beneficiary beneficiary); 
    Beneficiary findById(Long id);
    
    int deleteById(Long id, Long customerId);
   
    
	List<Beneficiary> findByCustomerId(Long customerId);
}