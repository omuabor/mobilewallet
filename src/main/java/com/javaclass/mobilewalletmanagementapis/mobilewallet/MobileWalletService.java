package com.javaclass.mobilewalletmanagementapis.mobilewallet;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.AccountUpdateRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.CreateWalletRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.DisableAccountRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.FetchAccountRequest;

import java.util.Collections;
import java.util.List;
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
        Optional<List<MobileWallet>> mobileWalletOptional = mobileWalletRepository
                .findByQueryItem(newMobileWallet.getPhoneNumber());
        if (mobileWalletOptional.isPresent()) {
            throw new IllegalStateException("This phone number is already resigtered");
        }

        ObjectMapper mapper = new ObjectMapper();
        MobileWallet mobileWallet = mapper.convertValue(newMobileWallet, MobileWallet.class);

        mobileWalletRepository.save(mobileWallet);
    }

    public List<MobileWallet> fetchAccount(FetchAccountRequest fetchAccountRequest) {

        String queryItem = fetchAccountRequest.getQueryItem();

        Optional<List<MobileWallet>> mobileWalletOptional = mobileWalletRepository
                .findByQueryItem(queryItem);
        if (mobileWalletOptional.isPresent()) {
            return mobileWalletOptional.get();
        }

        return Collections.emptyList();

    }

    public void updateAccount(AccountUpdateRequest accountUpdateRequest, String queryItem) {
        Optional<List<MobileWallet>> wallet = mobileWalletRepository.findByQueryItem(queryItem);

        ObjectMapper mapper = new ObjectMapper();
        MobileWallet update = mapper.convertValue(accountUpdateRequest, MobileWallet.class);

        if (wallet.isPresent()) {
            MobileWallet incumbentWallet = (MobileWallet) wallet.get();

            if (update.equals(incumbentWallet)) {
                throw new IllegalStateException("Nothing to update");
            }

            mobileWalletRepository.save(update);
        }

    }

    public void disableAccount(DisableAccountRequest disableAccountRequest, String queryItem) {
        Optional<List<MobileWallet>> wallet = mobileWalletRepository.findByQueryItem(queryItem);
        if (wallet.isPresent()) {
            disableAccountRequest.setAccountDisabled(!disableAccountRequest.isAccountDisabled());

            ObjectMapper mapper = new ObjectMapper();
            MobileWallet disabledAccount = mapper.convertValue(disableAccountRequest, MobileWallet.class);

            mobileWalletRepository.save(disabledAccount);
        }
    }

}
