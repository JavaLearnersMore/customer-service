package com.example.service;

import java.util.List;

import com.example.model.AddBeneficiaryRequest;
import com.example.model.Beneficiary;

public interface BeneficiaryService {
    Beneficiary addBeneficiary(Long customerId, AddBeneficiaryRequest request);
   
    List listBeneficiaries(Long customerId);
    
	void deleteBeneficiary(Long id, Long customerId);
}