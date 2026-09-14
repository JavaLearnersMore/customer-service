package com.example.dao;

import com.example.model.CustomerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL =
            "SELECT id, customer_no, name, email, mobile, dob, kyc_status, status "
          + "FROM customer WHERE id = ?";

    @Override
    public CustomerProfile findById(Long customerId) {
        List<CustomerProfile> results = jdbcTemplate.query(SQL, profileRowMapper(), customerId);
        return results.isEmpty() ? null : results.get(0);
    }

    private RowMapper<CustomerProfile> profileRowMapper() {
        return (rs, rowNum) -> new CustomerProfile(
                rs.getLong("id"),
                rs.getString("customer_no"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("mobile"),
                rs.getDate("dob").toLocalDate(),
                rs.getString("kyc_status"),
                rs.getString("status")
        );
    }
}