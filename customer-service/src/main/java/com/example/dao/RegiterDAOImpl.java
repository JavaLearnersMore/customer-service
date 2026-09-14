package com.example.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.model.Registration;

@Repository
public class RegiterDAOImpl implements RegiterDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long saveRegistration(Registration registration) {
        String sql = "INSERT INTO customer "
                + "(customer_no, name, email, mobile, dob, kyc_status, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, registration.getCustomerNo());
            ps.setString(2, registration.getName());
            ps.setString(3, registration.getEmail());
            ps.setString(4, registration.getMobile());
            ps.setDate(5, Date.valueOf(registration.getDob()));
            ps.setString(6, registration.getKycStatus());
            ps.setString(7, registration.getStatus());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}