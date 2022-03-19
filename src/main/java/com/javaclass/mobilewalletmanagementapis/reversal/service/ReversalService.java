package com.javaclass.mobilewalletmanagementapis.reversal.service;


import com.javaclass.mobilewalletmanagementapis.mobilewallet.respositories.MobileWalletRepository;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.services.MobileWalletService;
import com.javaclass.mobilewalletmanagementapis.reversal.entity.Balances;
import com.javaclass.mobilewalletmanagementapis.reversal.entity.ReversalResponse;
import com.javaclass.mobilewalletmanagementapis.reversal.entity.Transactions;
import com.javaclass.mobilewalletmanagementapis.reversal.repo.IBalanceRepo;
import com.javaclass.mobilewalletmanagementapis.reversal.repo.ITransactionRepo;
import com.javaclass.mobilewalletmanagementapis.transfer.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReversalService {
    @Autowired
    private ITransactionRepo iTransactionRepo;
    @Autowired
    private IBalanceRepo iBalanceRepo;

    ReversalResponse reversalResponse;

    @Autowired
    private TransferService transferService;

    private MobileWalletRepository mobileWalletRepository;

    @Autowired
    private MobileWalletService mobileWalletService;

    @Transactional
    public ResponseEntity<ReversalResponse> debitAccount(Transactions transactions, String corebankingResponseId){
        Balances balance = iBalanceRepo.findByAccountNumber(transactions.getAccountNumber());

        float newAccBalance = balance.getBalance() - transactions.getAmount();
        balance.setBalance(newAccBalance);
        iBalanceRepo.save(balance);

        Transactions newTransaction = new Transactions();
        newTransaction.setRequestId(corebankingResponseId);
        newTransaction.setAmount(transactions.getAmount());
        newTransaction.setAccountNumber(transactions.getAccountNumber());
        newTransaction.setTransactionType("DEBIT");
        newTransaction.setTransactionDate(java.time.LocalDateTime.now());
        iTransactionRepo.save(newTransaction);

        return ResponseEntity.ok().body(new ReversalResponse("000", "Successful", corebankingResponseId, "Debit successful"));
    }

    @Transactional
    public ReversalResponse creditAccount(Transactions transactions, String corebankingResponseId){
        Balances balance = iBalanceRepo.findByAccountNumber(transactions.getAccountNumber());

        float newAccBalance = balance.getBalance() + transactions.getAmount();
        balance.setBalance(newAccBalance);
        iBalanceRepo.save(balance);

        Transactions newTransaction = new Transactions();
        newTransaction.setRequestId(corebankingResponseId);
        newTransaction.setAmount(transactions.getAmount());
        newTransaction.setAccountNumber(transactions.getAccountNumber());
        newTransaction.setTransactionType("CREDIT");
        newTransaction.setTransactionDate(java.time.LocalDateTime.now());
        iTransactionRepo.save(newTransaction);

        new ReversalResponse("000", "Successful", corebankingResponseId, "Credit successful");
        return reversalResponse;
    }

    @Transactional
    public ReversalResponse reversal(Transactions toDebit, Transactions toCredit){
        String responseId = "REV-" + toDebit.getRequestId();
        debitAccount(toDebit, responseId);
        creditAccount(toCredit, responseId);

        reversalResponse = new ReversalResponse("000", "Successful", responseId, "Reversal successful for requestID " + toCredit.getRequestId() );
        return reversalResponse;
    }
}