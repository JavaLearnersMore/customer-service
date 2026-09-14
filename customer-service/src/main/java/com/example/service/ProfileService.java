package com.example.service;

import com.example.model.ChangeMpinRequest;
import com.example.model.CustomerProfile;

public interface ProfileService {
    CustomerProfile getProfile(Long customerId);
  
    void changeMpin(ChangeMpinRequest request);
}