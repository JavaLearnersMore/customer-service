package com.example.model;

public class RegistrationResponse {
    private Long id;
    private String customerNo;
    private String name;
    private String email;
    private String mobile;
    private String kycStatus;
    private String status;

    public RegistrationResponse(Registration r) {
        this.id = r.getId();
        this.customerNo = r.getCustomerNo();
        this.name = r.getName();
        this.email = r.getEmail();
        this.mobile = r.getMobile();
        this.kycStatus = r.getKycStatus();
        this.status = r.getStatus();
    }

    public Long getId() { return id; }
    public String getCustomerNo() { return customerNo; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public String getKycStatus() { return kycStatus; }
    public String getStatus() { return status; }
}