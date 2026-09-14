package com.example.service;

import com.example.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> getLinkedAccounts(Long customerId);
}
