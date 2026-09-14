package com.example.dao;

import com.example.model.CustomerProfile;

public interface CustomerDAO {
    CustomerProfile findById(Long customerId);
}