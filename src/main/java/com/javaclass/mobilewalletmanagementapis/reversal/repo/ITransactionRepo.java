package com.javaclass.mobilewalletmanagementapis.reversal.repo;

import com.javaclass.mobilewalletmanagementapis.reversal.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepo extends JpaRepository<Transactions,String> {
    List<Transactions> findByRequestId(String requestId);
}
