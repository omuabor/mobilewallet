package com.javaclass.mobilewalletmanagementapis.mobilewallet;

import java.util.List;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.AccountUpdateRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.CreateWalletRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.DisableAccountRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.FetchAccountRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.responses.AccountUpdateResponse;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.responses.CreateWalletResponse;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.responses.DisableAccountResponse;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.responses.FetchAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
                "000", "Success", "Account opened successfully");

        return new ResponseEntity<>(createWalletResponse, HttpStatus.OK);

    }

    @GetMapping("/fetch-account")
    @ResponseBody
    public FetchAccountResponse fetchAccount(@RequestBody FetchAccountRequest fetchAccountRequest) {
        List<MobileWallet> details = mobileWalletService.fetchAccount(fetchAccountRequest);

        return new FetchAccountResponse("000", "success", details);

    }

    @PutMapping("/update-account/{phoneNumber}")
    @ResponseBody
    public AccountUpdateResponse updateAccount(
            @RequestBody AccountUpdateRequest accountUpdateRequest,
            @PathVariable("phoneNumber") String phoneNumber) {

        mobileWalletService.updateAccount(accountUpdateRequest, phoneNumber);

        return new AccountUpdateResponse("000", "success", "Update successful");
    }

    @PutMapping("/disable-account/{phoneNumber}")
    @ResponseBody
    public DisableAccountResponse disableAccount(
            @RequestBody DisableAccountRequest disableAccountRequest,
            @PathVariable("phoneNumber") String phoneNumber) {
        mobileWalletService.disableAccount(disableAccountRequest, phoneNumber);

        return new DisableAccountResponse("000", "success", "Account disabled");
    }

}
