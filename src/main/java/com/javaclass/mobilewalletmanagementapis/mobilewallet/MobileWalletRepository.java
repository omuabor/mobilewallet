package com.javaclass.mobilewalletmanagementapis.mobilewallet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileWalletRepository extends JpaRepository<MobileWallet, String> {

    Optional<List<MobileWallet>> findByPhoneNumber(String phoneNumber);

}
