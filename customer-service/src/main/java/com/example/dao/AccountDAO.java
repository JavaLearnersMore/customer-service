package com.example.dao;

import com.example.model.Account;

import java.util.List;

public interface AccountDAO {
    List<Account> findByCustomerId(Long customerId);
}