package com.javaclass.mobilewalletmanagementapis.mobilewallet;

public class AccountUpdateResponse{
    private String code;
    private String message;
    private String otherDetails;
}

    public AccountUpdateResponse() {

    }

    public AccountUpdateResponse(String code, String message, String otherDetails) {
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

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

}
