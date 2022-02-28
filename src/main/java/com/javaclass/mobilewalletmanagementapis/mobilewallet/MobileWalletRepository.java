package com.javaclass.mobilewalletmanagementapis.mobilewallet;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileWalletRepository extends JpaRepository<MobileWallet, Long> {

    Optional<MobileWallet> findByWalletNumber(String walletNumber);

}
