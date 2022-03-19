package com.javaclass.mobilewalletmanagementapis.transfer.repo;


import com.javaclass.mobilewalletmanagementapis.transfer.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface TransferRepo extends JpaRepository <Transfer, String> {
}
