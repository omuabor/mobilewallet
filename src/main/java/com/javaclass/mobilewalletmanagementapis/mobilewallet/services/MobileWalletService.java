package com.javaclass.mobilewalletmanagementapis.mobilewallet.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.AccountUpdateRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.CreateWalletRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.DisableAccountRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.data.requests.FetchAccountRequest;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.entities.MobileWallet;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.respositories.MobileWalletRepository;

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

        Optional<MobileWallet> mobileWalletOptional = mobileWalletRepository
                .findByPhoneNumber(newMobileWallet.getPhoneNumber());

        if (mobileWalletOptional.isPresent()) {
            throw new IllegalStateException("This phone number is already resigtered");
        }

        ObjectMapper mapper = new ObjectMapper();
        MobileWallet mobileWallet = mapper.convertValue(newMobileWallet, MobileWallet.class);

        mobileWalletRepository.save(mobileWallet);
    }

    public List<MobileWallet> fetchAccount(FetchAccountRequest fetchAccountRequest) {

        String queryItem = fetchAccountRequest.getQueryItem();

        Optional<MobileWallet> mobileWalletOptional = mobileWalletRepository.findByPhoneNumber(queryItem);

        if (mobileWalletOptional.isPresent()) {
            return List.of(mobileWalletOptional.get());
        }

        return Collections.emptyList();

    }

    public void updateAccount(AccountUpdateRequest accountUpdateRequest) {
        String phoneNumber = accountUpdateRequest.getPhoneNumber();
        Optional<MobileWallet> mobileWalletOptional = mobileWalletRepository.findByPhoneNumber(phoneNumber);

        ObjectMapper mapper = new ObjectMapper();
        MobileWallet update = mapper.convertValue(accountUpdateRequest, MobileWallet.class);

        if (mobileWalletOptional.isPresent()) {

            MobileWallet incumbentWallet = mobileWalletOptional.get();
            if (update.equals(incumbentWallet)) {
                throw new IllegalStateException("Nothing to update");
            }

            mobileWalletRepository.save(update);

        }

    }

    public void disableAccount(DisableAccountRequest disableAccountRequest) {
        String phoneNumber = disableAccountRequest.getPhoneNumber();
        Optional<MobileWallet> wallet = mobileWalletRepository.findByPhoneNumber(phoneNumber);
        if (wallet.isPresent()) {
            MobileWallet disabledAccount = wallet.get();
            disabledAccount.setAccountDisabled(true);

            mobileWalletRepository.save(disabledAccount);
        }
    }

}
