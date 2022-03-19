package com.javaclass.mobilewalletmanagementapis.transfer.service;


import com.javaclass.mobilewalletmanagementapis.mobilewallet.entities.MobileWallet;
import com.javaclass.mobilewalletmanagementapis.mobilewallet.respositories.MobileWalletRepository;
import com.javaclass.mobilewalletmanagementapis.reversal.entity.Balances;
import com.javaclass.mobilewalletmanagementapis.reversal.entity.Transactions;
import com.javaclass.mobilewalletmanagementapis.reversal.repo.IBalanceRepo;
import com.javaclass.mobilewalletmanagementapis.reversal.repo.ITransactionRepo;
import com.javaclass.mobilewalletmanagementapis.transfer.entity.Transfer;
import com.javaclass.mobilewalletmanagementapis.transfer.repo.TransferRepo;
import com.javaclass.mobilewalletmanagementapis.transfer.utils.CreditDebitWallet;
import com.javaclass.mobilewalletmanagementapis.transfer.utils.TransferResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransferService {

    @Autowired
    private Transactions transactions;

    @Autowired
    private IBalanceRepo iBalanceRepo;

    @Autowired
    private ITransactionRepo iTransactionRepo;

    @Autowired
    private MobileWalletRepository mobileWalletRepository;

    @Autowired
    private TransferRepo transferRepo;

    @Autowired
    private CreditDebitWallet creditDebitWallet;

    @Transactional
    public TransferResponse transfer(Transfer transfer) {
            String responseString;
            float amount = transfer.getAmount();
            MobileWallet sourceAccount = mobileWalletRepository.findByPhoneNumber(transfer.getSourceAccountNumber()).orElse(null);
            MobileWallet destinationAccount = mobileWalletRepository.findByPhoneNumber(transfer.getDestinationAccountNumber()).orElse(null);
            if (destinationAccount == null || sourceAccount == null) {
                responseString = String.format("Please check account details and try again");
                return new TransferResponse("900", responseString, "Cannot process transaction", "");
            } else if (iBalanceRepo.findByAccountNumber(sourceAccount.getPhoneNumber()).getBalance() <= amount) {
                responseString = String.format("Insufficient Funds on %s's account, cannot proceed with transaction", sourceAccount.getCustomerName());
                return new TransferResponse("900", responseString, "Cannot process transaction", "");
            }
            Transfer savedTransfer = transferRepo.save(transfer);
            Transactions debitTransaction = new Transactions(savedTransfer.getRequestId(), java.time.LocalDateTime.now(), sourceAccount.getPhoneNumber(), amount, "DEBIT");
            Transactions creditTransaction = new Transactions(savedTransfer.getRequestId(), java.time.LocalDateTime.now(), destinationAccount.getPhoneNumber(), amount, "CREDIT");
            creditDebitWallet.creditWallet(creditTransaction, amount);
            creditDebitWallet.debitWallet(debitTransaction, amount);
            responseString = String.format("%.2f successfully transferred between %s and %s", amount, sourceAccount.getCustomerName(), destinationAccount.getCustomerName());
            return new TransferResponse("200", responseString, "Transaction successful", "");
    }

    public Balances getBalance(String accountnumber) {
        return iBalanceRepo.findByAccountNumber(accountnumber);
    }

}
