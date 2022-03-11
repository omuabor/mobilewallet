package com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests;

public class DisableAccountRequest {

    private long requestId;
    private String phoneNumber;
    private String bankVerificationNumber;
    private String emailAddress;
    private boolean accountDisabled;

    public DisableAccountRequest() {

    }

    public DisableAccountRequest(
            String phoneNumber,
            String bankVerificationNumber,
            String emailAddress,
            boolean accountDisabled) {
        this.phoneNumber = phoneNumber;
        this.bankVerificationNumber = bankVerificationNumber;
        this.emailAddress = emailAddress;
        this.accountDisabled = accountDisabled;
    }

    public DisableAccountRequest(
            long requestId,
            String phoneNumber,
            String bankVerificationNumber,
            String emailAddress,
            boolean accountDisabled) {
        this.requestId = requestId;
        this.phoneNumber = phoneNumber;
        this.bankVerificationNumber = bankVerificationNumber;
        this.emailAddress = emailAddress;
        this.accountDisabled = accountDisabled;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBankVerificationNumber() {
        return bankVerificationNumber;
    }

    public void setBankVerificationNumber(String bankVerificationNumber) {
        this.bankVerificationNumber = bankVerificationNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isAccountDisabled() {
        return accountDisabled;
    }

    public void setAccountDisabled(boolean accountDisabled) {
        this.accountDisabled = accountDisabled;
    }

}