package com.javaclass.mobilewalletmanagementapis.mobilewallet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "WLT_T_ACCOUNT_DETAILS")
public class MobileWallet implements Serializable {
    @Id
    @SequenceGenerator(name = "requestId_sequence", sequenceName = "requestId_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requestId_sequence")
    private long requestId;

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
    private String dateOfBirth;
    private boolean accountDisabled = false;
    private String queryItem;

    public MobileWallet() {

    }

    public MobileWallet(
            String firstName,
            String lastName,
            String phoneNumber,
            String gender,
            String secretWord,
            String bankVerificationNumber,
            String address,
            String accountType,
            String dateOfBirth,
            boolean accountDisabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.secretWord = secretWord;
        this.bankVerificationNumber = bankVerificationNumber;
        this.address = address;
        this.accountType = accountType;
        this.dateOfBirth = dateOfBirth;
        this.accountDisabled = accountDisabled
    }

    public MobileWallet(
            long requestId,
            String firstName,
            String lastName,
            String phoneNumber,
            String gender,
            String secretWord,
            String bankVerificationNumber,
            String address,
            String referralCode,
            String accountType,
            String emailAddress,
            String dateOfBirth,
            boolean accountDisabled
            String queryItem) {
        this.requestId = requestId;
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
        this.dateOfBirth = dateOfBirth;
        this.accountDisabled = accountDisabled;
        this.queryItem = queryItem;
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

    public boolean getAccountDisabled() {
        return accountDisabled;
    }

    public void setAccountDisabled(boolean accountDisabled) {
        this.accountDisabled = accountDisabled;
    }

    @Override
    public String toString() {
        return "MobileWallet [accountType=" + accountType + ", address=" + address + ", bankVerificationNumber="
                + bankVerificationNumber + ", dateOfBirth=" + dateOfBirth + ", emailAddress=" + emailAddress
                + ", firstName=" + firstName + ", gender=" + gender + ", lastName=" + lastName + ", phoneNumber="
                + phoneNumber + ", referralCode=" + referralCode + ", requestId=" + requestId + ", accountDisabled=" 
                + accountDisabled + "]";
    }

}