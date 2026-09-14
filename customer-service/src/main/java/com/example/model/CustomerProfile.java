package com.example.model;

import java.time.LocalDate;

public class CustomerProfile {

    private Long id;
    private String customerNo;
    private String name;
    private String email;
    private String mobile;
    private LocalDate dob;
    private String kycStatus;
    private String status;

    public CustomerProfile() {
    }

    public CustomerProfile(Long id, String customerNo, String name, String email, String mobile,
                            LocalDate dob, String kycStatus, String status) {
        this.id = id;
        this.customerNo = customerNo;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.dob = dob;
        this.kycStatus = kycStatus;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerNo() { return customerNo; }
    public void setCustomerNo(String customerNo) { this.customerNo = customerNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getKycStatus() { return kycStatus; }
    public void setKycStatus(String kycStatus) { this.kycStatus = kycStatus; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}