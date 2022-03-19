package com.javaclass.mobilewalletmanagementapis.transfer.controller;

import com.javaclass.mobilewalletmanagementapis.reversal.entity.Transactions;
import com.javaclass.mobilewalletmanagementapis.transfer.utils.CreditDebitWallet;
import com.javaclass.mobilewalletmanagementapis.transfer.utils.TransferResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("api/transaction")
public class CreditAndDebitController {

    @Autowired
    private CreditDebitWallet creditDebitWallet;

    @PostMapping("/credit")
    public ResponseEntity<TransferResponse> newCredit (@RequestBody Transactions creditTransaction) {

        return new ResponseEntity<>(creditDebitWallet.creditWallet(creditTransaction, creditTransaction.getAmount()), HttpStatus.OK);
    }

    @PostMapping("/debit")
    public ResponseEntity<TransferResponse> newDebit (@RequestBody Transactions debitTransaction) {
        return new ResponseEntity<>(creditDebitWallet.debitWallet(debitTransaction, debitTransaction.getAmount()), HttpStatus.OK);
    }
}
