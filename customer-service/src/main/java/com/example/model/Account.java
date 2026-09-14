package com.example.model;

import java.math.BigDecimal;

public class Account {

    private String accountNumber;
    private String ifsc;
    private String accountType;
    private String nickname;
    private boolean isPrimary;
    private BigDecimal availableBalance;
    private BigDecimal ledgerBalance;
    private String status;

    public Account() {
    }

    public Account(String accountNumber, String ifsc, String accountType, String nickname,
                   boolean isPrimary, BigDecimal availableBalance, BigDecimal ledgerBalance, String status) {
        this.accountNumber = accountNumber;
        this.ifsc = ifsc;
        this.accountType = accountType;
        this.nickname = nickname;
        this.isPrimary = isPrimary;
        this.availableBalance = availableBalance;
        this.ledgerBalance = ledgerBalance;
        this.status = status;
    }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getIfsc() { return ifsc; }
    public void setIfsc(String ifsc) { this.ifsc = ifsc; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public boolean isPrimary() { return isPrimary; }
    public void setPrimary(boolean primary) { isPrimary = primary; }

    public BigDecimal getAvailableBalance() { return availableBalance; }
    public void setAvailableBalance(BigDecimal availableBalance) { this.availableBalance = availableBalance; }

    public BigDecimal getLedgerBalance() { return ledgerBalance; }
    public void setLedgerBalance(BigDecimal ledgerBalance) { this.ledgerBalance = ledgerBalance; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}