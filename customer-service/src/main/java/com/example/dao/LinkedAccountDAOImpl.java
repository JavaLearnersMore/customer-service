package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.LinkedAccount;

@Repository
public class LinkedAccountDAOImpl implements LinkedAccountDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveAccount(LinkedAccount account) {
        String sql = "INSERT INTO linked_account "
                + "(customer_id, account_number, ifsc, account_type, nickname, is_primary) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                account.getCustomerId(),
                account.getAccountNumber(),
                account.getIfsc(),
                account.getAccountType(),
                account.getNickname(),
                account.isPrimary()
        );
       }

	}
