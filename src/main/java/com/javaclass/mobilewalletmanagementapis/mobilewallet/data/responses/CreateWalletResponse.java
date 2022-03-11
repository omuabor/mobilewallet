package com.javaclass.mobilewalletmanagementapis.mobilewallet.data.responses;

public class CreateWalletResponse extends BaseResponse {
    private String otherDetails;

    public CreateWalletResponse() {

    }

    public CreateWalletResponse(String code, String message, String otherDetails) {
        super(code, message);
        this.otherDetails = otherDetails;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

}
