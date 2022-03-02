package com.javaclass.mobilewalletmanagementapis.mobilewallet;

import java.util.Optional;
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

    public void addNewAccount(MobileWallet mobileWallet) {
        Optional<List<MobileWallet>> mobileWalletOptional = mobileWalletRepository
                .findByQueryItem(mobileWallet.getPhoneNumber());
        if (mobileWalletOptional.isPresent()) {
            throw new IllegalStateException("This phone number is already resigtered");
        }

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

}
