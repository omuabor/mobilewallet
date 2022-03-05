package com.javaclass.mobilewalletmanagementapis.mobilewallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.AccountUpdateRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.CreateWalletRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.DisableAccountRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.FetchAccountRequest;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileWalletService {
    private final MobileWalletRepository mobileWalletRepository;

    @Autowired
    MobileWalletService(MobileWalletRepository mobileWalletRepository) {
        this.mobileWalletRepository = mobileWalletRepository;
    }

    public void addNewAccount(CreateWalletRequest newMobileWallet) {

        // if phone exists, raise exception

        /*
         * Optional<List<MobileWallet>> mobileWalletOptional = mobileWalletRepository
         * .findByPhoneNumber(newMobileWallet.getPhoneNumber());
         * 
         * if (mobileWalletOptional.isPresent()) {
         * throw new IllegalStateException("This phone number is already resigtered");
         * }
         */

        ObjectMapper mapper = new ObjectMapper();
        MobileWallet mobileWallet = mapper.convertValue(newMobileWallet, MobileWallet.class);

        mobileWalletRepository.save(mobileWallet);
    }

    public List<MobileWallet> fetchAccount(FetchAccountRequest fetchAccountRequest) {

        String queryItem = fetchAccountRequest.getQueryItem();

        Optional<List<MobileWallet>> mobileWalletOptional = mobileWalletRepository
                .findByPhoneNumber(queryItem);
        if (mobileWalletOptional.isPresent()) {
            return mobileWalletOptional.get();
        }

        return Collections.emptyList();

    }

    public void updateAccount(AccountUpdateRequest accountUpdateRequest, String queryItem) {
        Optional<List<MobileWallet>> mobileWalletOptional = mobileWalletRepository.findByPhoneNumber(queryItem);

        ObjectMapper mapper = new ObjectMapper();
        MobileWallet update = mapper.convertValue(accountUpdateRequest, MobileWallet.class);

        if (mobileWalletOptional.isPresent()) {

            MobileWallet incumbentWallet = mobileWalletOptional.get().get(0);
            if (update.equals(incumbentWallet)) {
                throw new IllegalStateException("Nothing to update");
            }

            mobileWalletRepository.save(update);

        }

    }

    public void disableAccount(DisableAccountRequest disableAccountRequest, String queryItem) {
        Optional<List<MobileWallet>> wallet = mobileWalletRepository.findByPhoneNumber(queryItem);
        if (wallet.isPresent()) {
            disableAccountRequest.setAccountDisabled(!disableAccountRequest.isAccountDisabled());

            ObjectMapper mapper = new ObjectMapper();
            MobileWallet disabledAccount = mapper.convertValue(disableAccountRequest, MobileWallet.class);

            mobileWalletRepository.save(disabledAccount);
        }
    }

}
