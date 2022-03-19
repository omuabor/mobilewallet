package com.javaclass.mobilewalletmanagementapis.transfer.controller;


import com.javaclass.mobilewalletmanagementapis.reversal.entity.Balances;
import com.javaclass.mobilewalletmanagementapis.reversal.entity.ReversalResponse;
import com.javaclass.mobilewalletmanagementapis.transfer.entity.Transfer;
import com.javaclass.mobilewalletmanagementapis.transfer.service.TransferService;
import com.javaclass.mobilewalletmanagementapis.transfer.utils.TransferResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

//    @PostMapping("/create-account")
//    public WalletAccount createNewWallet(@RequestBody WalletAccount walletAccount) {
//        return transferService.createWallet(walletAccount);
//    }
//
    @GetMapping("/getbalance/{accountnumber}")
    public Balances getBalance(@PathVariable String accountnumber){

        return transferService.getBalance(accountnumber);
    }
//
//    @PutMapping("/update-account")
//    public WalletAccount updateProduct (@RequestBody WalletAccount walletAccount) {
//        return transferService.updateWallet(walletAccount);
//    }

    @PostMapping("/new")
    public ResponseEntity<TransferResponse> createTransfer (@RequestBody Transfer transferTransaction) {
        return new ResponseEntity<>(transferService.transfer(transferTransaction), HttpStatus.OK) ;
    }



}
