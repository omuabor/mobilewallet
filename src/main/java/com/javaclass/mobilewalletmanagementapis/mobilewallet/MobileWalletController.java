package com.javaclass.mobilewalletmanagementapis.mobilewallet;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<CreateWalletResponse> openAccount(@RequestBody MobileWallet newMobileWallet) {
        mobileWalletService.addNewAccount(newMobileWallet);

        CreateWalletResponse createWalletResponse = new CreateWalletResponse(
                "000", "Success", "Account opened successfully");

        return new ResponseEntity<>(createWalletResponse, HttpStatus.OK);

    }

    @GetMapping("/fetch-account/{queryItem}")
    @ResponseBody
    public FetchAccountResponse fetchAccount(@RequestBody FetchAccountRequest fetchAccountRequest) {
        List<MobileWallet> details = mobileWalletService.fetchAccount(fetchAccountRequest);

        return new FetchAccountResponse("000", "success", details);

    }


    @PutMapping("/update-account")
    @ResponseBody
    public UpdateAccountResponse updateAccount(@RequestBody UpdateAccountRequest updateAccountRequest) {
        mobileWalletService.updateAccount(updateAccountRequest);

        return new UpdateAccountResponse("000", "success", "Account updated");
    }

    @PutMapping("/disable-account")
    @ResponseBody
    public DisableAccountResponse disableAccount(@RequestBody DisableAccountRequest disableAccountRequest) {
        mobileWalletService.disableAccount(disableAccountRequest);

        return new DisableAccountResponse("000", "success", "Account disabled");
    }

}
