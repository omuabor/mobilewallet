package com.javaclass.mobilewalletmanagementapis.reversal.controller;

import com.javaclass.mobilewalletmanagementapis.reversal.dto.ReversalRequest;
import com.javaclass.mobilewalletmanagementapis.reversal.dto.ReversalResponse;
import com.javaclass.mobilewalletmanagementapis.reversal.entity.Transactions;
import com.javaclass.mobilewalletmanagementapis.reversal.repo.ITransactionRepo;
import com.javaclass.mobilewalletmanagementapis.reversal.service.ReversalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class Reversal {
    private static final Logger LOGGER = LoggerFactory.getLogger(Reversal.class);

    @Autowired
    private ReversalService reversalService;
    @Autowired
    private ITransactionRepo iTransactionRepo;

    ReversalResponse reversalResponse;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reversal")
    @Transactional
    public ResponseEntity<ReversalResponse> createReversals(@RequestBody ReversalRequest reversalRequest) {
        // TODO: Move implementation to service layer
        List<Transactions> getTransaction = iTransactionRepo.findByRequestId(reversalRequest.getRequestId());
        LOGGER.info("Lookup transaction for ID {}", reversalRequest.getRequestId());
        List<Transactions> getReversedTransaction = iTransactionRepo.findByRequestId("REV-"+reversalRequest.getRequestId());
        LOGGER.info("Lookup reversed transaction for ID {}", "REV-"+reversalRequest.getRequestId());

        if (getTransaction.size() == 0) {
            reversalResponse = new ReversalResponse("900", "Failed", null, "Transaction not found");
            return ResponseEntity.ok().body(reversalResponse);
        }
        if (getReversedTransaction.size() != 0) {
            reversalResponse = new ReversalResponse("900", "Failed", null, "Transaction has already been reversed");
            return ResponseEntity.ok().body(reversalResponse);
        }

        // To determine what account to debit or credit
        Transactions toDebit = getTransaction.get(1), toCredit = getTransaction.get(0);
        if (getTransaction.get(0).getTransactionType() == "DEBIT"){
            toCredit = getTransaction.get(0);
            toDebit = getTransaction.get(1);
        }

        ReversalResponse result = reversalService.reversal(toDebit, toCredit);
        LOGGER.info("Reversal response- {}", result);
        return ResponseEntity.ok().body(result);
    }
}
