package com.javaclass.mobilewalletmanagementapis.mobilewallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/mobilewallet")
public class MobileWalletController {

    private final MobileWalletService mobileWalletService;

    @Autowired
    MobileWalletController(MobileWalletService mobileWalletService) {
        this.mobileWalletService = mobileWalletService;
    }

    @PostMapping("/open-account")
    public ResponseEntity<CreateWalletResponse> openAccount(@RequestBody MobileWallet newMobileWallet) {
        mobileWalletService.addNewAccount(newMobileWallet);

        CreateWalletResponse createWalletResponse = new CreateWalletResponse(
                "000", "Success", "Account opened successfully");

        return new ResponseEntity<>(createWalletResponse, HttpStatus.OK);

    }

    // @GetMapping("/fetch-account")
    // public ResponseEntity<FetchAccountResponse> fetchAccount(String
    // queryItemType, String queryItem) {

    // }
}
