package com.example.dao;

import com.example.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

 
    private static final String SQL =
            "SELECT account_number, ifsc, account_type, nickname, is_primary "
          + "FROM customer_db.linked_account WHERE customer_id = ?";

    @Override
    public List<Account> findByCustomerId(Long customerId) {
        return jdbcTemplate.query(SQL, accountRowMapper(), customerId);
    }

    private RowMapper<Account> accountRowMapper() {
        return (rs, rowNum) -> new Account(
                rs.getString("account_number"),
                rs.getString("ifsc"),
                rs.getString("account_type"),
                rs.getString("nickname"),
                rs.getBoolean("is_primary"),
                new BigDecimal("49500.00"),   
                new BigDecimal("49500.00"),  
                "ACTIVE"                     
        );
    }
}