package com.javaclass.mobilewalletmanagementapis.mobilewallet.controllers;

import java.util.List;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.entities.MobileWallet;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.AccountUpdateRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.CreateWalletRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.DisableAccountRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.FetchAccountRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.responses.AccountUpdateResponse;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.responses.CreateWalletResponse;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.responses.DisableAccountResponse;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.responses.FetchAccountResponse;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.responses.ResponseEnum;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.services.MobileWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api/v1/mobilewallet")
public class MobileWalletController {

    private final MobileWalletService mobileWalletService;

    @Autowired
    MobileWalletController(MobileWalletService mobileWalletService) {
        this.mobileWalletService = mobileWalletService;
    }

    @PostMapping("/open-account")
    public ResponseEntity<CreateWalletResponse> openAccount(@RequestBody CreateWalletRequest newMobileWallet) {
        mobileWalletService.addNewAccount(newMobileWallet);

        CreateWalletResponse createWalletResponse = new CreateWalletResponse(
                ResponseEnum.SUCCESS.getCode(),
                ResponseEnum.SUCCESS.getMessage(),
                ResponseEnum.SUCCESS.getDescription());

        return new ResponseEntity<>(createWalletResponse, HttpStatus.OK);

    }

    @GetMapping("/fetch-account")
    @ResponseBody
    public FetchAccountResponse fetchAccount(@RequestBody FetchAccountRequest fetchAccountRequest) {
        List<MobileWallet> details = mobileWalletService.fetchAccount(fetchAccountRequest);

        return new FetchAccountResponse(
                ResponseEnum.SUCCESS.getCode(),
                ResponseEnum.SUCCESS.getMessage(),
                details);

    }

    @PutMapping("/update-account")
    @ResponseBody
    public AccountUpdateResponse updateAccount(
            @RequestBody AccountUpdateRequest accountUpdateRequest) {

        mobileWalletService.updateAccount(accountUpdateRequest);

        return new AccountUpdateResponse(
                ResponseEnum.SUCCESS.getCode(),
                ResponseEnum.SUCCESS.getMessage(),
                "Update successful");
    }

    @PutMapping("/disable-account")
    @ResponseBody
    public DisableAccountResponse disableAccount(@RequestBody DisableAccountRequest disableAccountRequest) {
        mobileWalletService.disableAccount(disableAccountRequest);

        return new DisableAccountResponse(
                ResponseEnum.SUCCESS.getCode(),
                ResponseEnum.SUCCESS.getMessage(),
                "Account disabled");
    }

}
