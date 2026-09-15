package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BeneficiaryDAO;
import com.example.model.AddBeneficiaryRequest;
import com.example.model.Beneficiary;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private static final int COOLING_PERIOD_MINUTES = 30;

    @Autowired
    private BeneficiaryDAO beneficiaryDAO;

    @Override
    public Beneficiary addBeneficiary(Long customerId, AddBeneficiaryRequest request) {
        if (request.getBeneName() == null || request.getBeneName().isBlank()) {
            throw new IllegalArgumentException("beneName is required");
        }
        if (request.getAccountNumber() == null || request.getAccountNumber().isBlank()) {
            throw new IllegalArgumentException("accountNumber is required");
        }
        if (request.getIfsc() == null || request.getIfsc().isBlank()) {
            throw new IllegalArgumentException("ifsc is required");
        }

        Beneficiary b = new Beneficiary();
        b.setCustomerId(customerId);
        b.setBeneName(request.getBeneName());
        b.setAccountNumber(request.getAccountNumber());
        b.setIfsc(request.getIfsc());
        b.setStatus("PENDING");
        b.setCoolingPeriodEnds(LocalDateTime.now().plusMinutes(COOLING_PERIOD_MINUTES));

        beneficiaryDAO.saveBeneficiary(b);
        return b;
    }
    @Override
    public void deleteBeneficiary(Long id, Long customerId) {
        int rows = beneficiaryDAO.deleteById(id, customerId);
        if (rows == 0) {
            throw new IllegalArgumentException("Beneficiary not found for this customer: " + id);
        }
}
	@Override
	public List<Beneficiary> listBeneficiaries(Long customerId) {
	    List<Beneficiary> beneficiaries = beneficiaryDAO.findByCustomerId(customerId);
	    if (beneficiaries.isEmpty()) {
	        throw new IllegalArgumentException("No beneficiaries found for this customer: " + customerId);
	    }
	    return beneficiaries;
	}
	
}