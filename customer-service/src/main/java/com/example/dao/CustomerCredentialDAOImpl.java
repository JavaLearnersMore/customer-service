package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.CustomerCredential;

import java.util.List;

@Repository
public class CustomerCredentialDAOImpl implements CustomerCredentialDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveCredential(CustomerCredential credential) {
        String sql = "INSERT INTO customer_credential "
                + "(customer_id, username, password_hash, mpin_hash) "
                + "VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                credential.getCustomerId(),
                credential.getUsername(),
                credential.getPasswordHash(),
                credential.getMpinHash()
        );
    }
    
    @Override
    public int updateMpinHash(Long customerId, String newMpinHash) {
        String sql = "UPDATE customer_credential SET mpin_hash = ? WHERE customer_id = ?";
        return jdbcTemplate.update(sql, newMpinHash, customerId);
    }


    @Override
    public CustomerCredential findByUsername(String username) {
        String sql = "SELECT customer_id, username, password_hash, mpin_hash "
                + "FROM customer_credential WHERE username = ?";

        List<CustomerCredential> results = jdbcTemplate.query(sql, (rs, rowNum) -> {
            CustomerCredential c = new CustomerCredential();
            c.setCustomerId(rs.getLong("customer_id"));
            c.setUsername(rs.getString("username"));
            c.setPasswordHash(rs.getString("password_hash"));
            c.setMpinHash(rs.getString("mpin_hash"));
            return c;
        }, username);

        return results.isEmpty() ? null : results.get(0);
    }



	@Override
	public CustomerCredential findByHashMpin(String hashMpin) {
		 String sql = "SELECT * FROM customer_credential WHERE mpin_hash = ?";

		    List<CustomerCredential> results = jdbcTemplate.query(
		        sql,
		        (rs, rowNum) -> {
		            CustomerCredential credential = new CustomerCredential();

		            credential.setId(rs.getLong("id"));
		            credential.setCustomerId(rs.getLong("customer_id"));
		            credential.setUsername(rs.getString("username"));
		            credential.setPasswordHash(rs.getString("password_hash"));
		            credential.setMpinHash(rs.getString("mpin_hash"));

		            return credential;
		        },
		        hashMpin
		    );

		    return results.isEmpty() ? null : results.get(0);
	}



	@Override
	public CustomerCredential decodeHashMpin(String custid) {
		String sql = "SELECT * FROM customer_credential WHERE customer_id = ?";

	    List<CustomerCredential> results = jdbcTemplate.query(
	        sql,
	        (rs, rowNum) -> {
	            CustomerCredential credential = new CustomerCredential();

	            credential.setId(rs.getLong("id"));
	            credential.setCustomerId(rs.getLong("customer_id"));
	            credential.setUsername(rs.getString("username"));
	            credential.setPasswordHash(rs.getString("password_hash"));
	            credential.setMpinHash(rs.getString("mpin_hash"));

	            return credential;
	        },
	        custid
	    );

	    return results.isEmpty() ? null : results.get(0);
}



	@Override
	public CustomerCredential findByCustomerId(Long customerId) {
		 String sql = "SELECT customer_id, username, password_hash, mpin_hash "
		            + "FROM customer_credential WHERE customer_id = ?";

		    List<CustomerCredential> results = jdbcTemplate.query(sql, (rs, rowNum) ->{
		        CustomerCredential c = new CustomerCredential();
		        c.setCustomerId(rs.getLong("customer_id"));
		        c.setUsername(rs.getString("username"));
		        c.setPasswordHash(rs.getString("password_hash"));
		        c.setMpinHash(rs.getString("mpin_hash"));
		        return c;
		    }, customerId);

		    return results.isEmpty() ? null : results.get(0);
	}

}

