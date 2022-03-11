package com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests;

public class CreateWalletRequest {

    private long requestId;
    private String dateOfBirth;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String secretWord;
    private String bankVerificationNumber;
    private String address;
    private String referralCode;
    private String accountType;
    private String emailAddress;

    public CreateWalletRequest() {

    }

    public CreateWalletRequest(
            long requestId,
            String dateOfBirth,
            String firstName,
            String lastName,
            String phoneNumber,
            String gender,
            String secretWord,
            String bankVerificationNumber,
            String address,
            String referralCode,
            String accountType,
            String emailAddress) {

        this.requestId = requestId;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.secretWord = secretWord;
        this.bankVerificationNumber = bankVerificationNumber;
        this.address = address;
        this.referralCode = referralCode;
        this.accountType = accountType;
        this.emailAddress = emailAddress;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }

    public String getBankVerificationNumber() {
        return bankVerificationNumber;
    }

    public void setBankVerificationNumber(String bankVerificationNumber) {
        this.bankVerificationNumber = bankVerificationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
