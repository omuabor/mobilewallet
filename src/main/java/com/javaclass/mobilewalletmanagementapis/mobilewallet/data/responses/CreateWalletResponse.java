package com.javaclass.mobilewalletmanagementapis.mobilewallet.data.responses;

public class CreateWalletResponse {
    private String code;
    private String message;
    private String otherDetails;

    public CreateWalletResponse(
            String code,
            String message,
            String otherDetails) {
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
