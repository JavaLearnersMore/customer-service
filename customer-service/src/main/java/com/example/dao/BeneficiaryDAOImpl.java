package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.model.Beneficiary;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class BeneficiaryDAOImpl implements BeneficiaryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveBeneficiary(Beneficiary b) {
        String sql = "INSERT INTO beneficiary "
                + "(customer_id, bene_name, account_number, ifsc, status, cooling_period_ends) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           ps.setLong(1, b.getCustomerId());
            ps.setString(2, b.getBeneName());
            ps.setString(3, b.getAccountNumber());
            ps.setString(4, b.getIfsc());
            ps.setString(5, b.getStatus());
            ps.setTimestamp(6, Timestamp.valueOf(b.getCoolingPeriodEnds()));
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            b.setId(keyHolder.getKey().longValue());
        }
        return rows;
    }

    @Override
    public Beneficiary findById(Long id) {
        String sql = "SELECT id, customer_id, bene_name, account_number, ifsc, status, cooling_period_ends "
                + "FROM beneficiary WHERE id = ?";

        List<Beneficiary> results = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Beneficiary b = new Beneficiary();
            b.setId(rs.getLong("id"));
          b.setCustomerId(rs.getLong("customer_id"));
            b.setBeneName(rs.getString("bene_name"));
            b.setAccountNumber(rs.getString("account_number"));
            b.setIfsc(rs.getString("ifsc"));
            b.setStatus(rs.getString("status"));
            b.setCoolingPeriodEnds(rs.getTimestamp("cooling_period_ends").toLocalDateTime());
            return b;
        }, id);

        return results.isEmpty() ? null : results.get(0);
    }
    @Override
    public int deleteById(Long id, Long customerId) {
       
        String sql = "DELETE FROM beneficiary WHERE id = ? AND customer_id = ?";
        return jdbcTemplate.update(sql, id, customerId);
    }
    @Override
    public List<Beneficiary> findByCustomerId(Long customerId) {
        String sql = "SELECT id, customer_id, bene_name, account_number, ifsc, status, cooling_period_ends "
                + "FROM beneficiary WHERE customer_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Beneficiary b = new Beneficiary();
            b.setId(rs.getLong("id"));
            b.setCustomerId(rs.getLong("customer_id"));
            b.setBeneName(rs.getString("bene_name"));
            b.setAccountNumber(rs.getString("account_number"));
            b.setIfsc(rs.getString("ifsc"));
            b.setStatus(rs.getString("status"));
            b.setCoolingPeriodEnds(rs.getTimestamp("cooling_period_ends").toLocalDateTime());
            return b;
        }, customerId);
   }
}