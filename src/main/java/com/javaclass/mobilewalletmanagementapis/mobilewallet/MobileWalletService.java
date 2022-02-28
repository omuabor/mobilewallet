package com.javaclass.mobilewalletmanagementapis.mobilewallet;

import java.util.Optional;

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
        Optional<MobileWallet> mobileWalletOptional = mobileWalletRepository
                .findByWalletNumber(mobileWallet.getWalletNumber());
        if (mobileWalletOptional.isPresent()) {
            throw new IllegalStateException("This phone number is already resigtered");
        }

        mobileWalletRepository.save(mobileWallet);
    }

}
