package com.javaclass.mobilewalletmanagementapis.mobilewallet.data.responses;

import java.util.List;

public class FetchAccountResponse {
    private String code;
    private String message;
    private List<MobileWallet> otherDetails;

    public FetchAccountResponse() {

    }

    public FetchAccountResponse(String code, String message, List<MobileWallet> otherDetails) {
        this.code = code;
        this.message = message;
        this.otherDetails = otherDetails;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MobileWallet> getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(List<MobileWallet> otherDetails) {
        this.otherDetails = otherDetails;
    }

}
