package com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests;

public class DisableAccountRequest{
    
    private long requestId;
    private String phoneNumber;
    private String bankVerificationNumber;
    private String emailAddress;

    public DisableAccountRequest() {

    }

    public DisableAccountRequest(
        long requestId,
        String phoneNumber,
        String bankVerificationNumber,
        String emailAddress) {
            this.requestId = requestId;
            this.phoneNumber = phoneNumber;
            this.bankVerificationNumber = bankVerificationNumber;
            this.emailAddress = emailAddress;
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
}