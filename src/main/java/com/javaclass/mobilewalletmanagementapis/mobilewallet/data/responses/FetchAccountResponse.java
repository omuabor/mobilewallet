package com.javaclass.mobilewalletmanagementapis.mobilewallet.data.responses;

import java.util.List;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.entities.MobileWallet;

public class FetchAccountResponse extends BaseResponse {
    private List<MobileWallet> otherDetails;

    public FetchAccountResponse() {

    }

    public FetchAccountResponse(String code, String message, List<MobileWallet> otherDetails) {
        super(code, message);
        this.otherDetails = otherDetails;
    }

    public void setOtherDetails(List<MobileWallet> otherDetails) {
        this.otherDetails = otherDetails;
    }

    public List<MobileWallet> getOtherDetails() {
        return otherDetails;
    }

}
