package com.javaclass.mobilewalletmanagementapis.transfer.utils;

import com.javaclass.mobilewalletmanagementapis.mobilewallet.entities.MobileWallet;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.respositories.MobileWalletRepository;
import com.javaclass.mobilewalletmanagementapis.reversal.entity.Balances;
import com.javaclass.mobilewalletmanagementapis.reversal.entity.Transactions;
import com.javaclass.mobilewalletmanagementapis.reversal.repo.IBalanceRepo;
import com.javaclass.mobilewalletmanagementapis.reversal.repo.ITransactionRepo;
import com.javaclass.mobilewalletmanagementapis.transfer.entity.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CreditDebitWallet {
    @Autowired
    private MobileWallet mobileWallet;

    @Autowired
    private ITransactionRepo iTransactionRepo;

    @Autowired
    private IBalanceRepo iBalanceRepo;

    @Autowired
    private MobileWalletRepository mobileWalletRepository;

    public TransferResponse creditWallet (Transactions transaction, float amount) {
        MobileWallet wallet = mobileWalletRepository.findByPhoneNumber(transaction.getAccountNumber()).orElse(null);
        if (wallet != null) {
            Balances existingBalance = iBalanceRepo.findByAccountNumber(wallet.getPhoneNumber());
            existingBalance.setBalance(existingBalance.getBalance() + amount);
            iBalanceRepo.save(existingBalance);
            transaction.setTransactionDate(java.time.LocalDateTime.now());
            iTransactionRepo.save(transaction);
            return new TransferResponse("200", "Credit successful", "Transaction successful", "");
        }
        return new TransferResponse("404", "Credit transaction failed", String.format (("account number %s does not exist"), transaction.getAccountNumber()), "");
    }

    public TransferResponse debitWallet (Transactions transaction, float amount) {
        MobileWallet wallet = mobileWalletRepository.findByPhoneNumber(transaction.getAccountNumber()).orElse(null);
        if (wallet != null) {
            Balances existingBalance = iBalanceRepo.findByAccountNumber(wallet.getPhoneNumber());
            if (existingBalance.getBalance() > amount) {
                existingBalance.setBalance(existingBalance.getBalance() - amount);
                iBalanceRepo.save(existingBalance);
                transaction.setTransactionDate(java.time.LocalDateTime.now());
                iTransactionRepo.save(transaction);
                return new TransferResponse("200", "Debit successful", "Transaction successful", "");
            } else {
                return new TransferResponse("900", "Debit transaction failed", "Insufficient balance on account", "");
            }
        }
        return new TransferResponse("404", "Debit transaction failed", "Invalid account", "");
    }
}
