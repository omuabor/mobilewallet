package com.javaclass.mobilewalletmanagementapis.mobilewallet.respositories;

import java.util.Optional;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.entities.MobileWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileWalletRepository extends JpaRepository<MobileWallet, String> {

    Optional<MobileWallet> findByPhoneNumber(String phoneNumber);

}
