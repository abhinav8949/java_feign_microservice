package com.transaction.controller;

import com.mini_lib.response_DTO.StatusResponse;
import com.transaction.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.transaction.utility.Constants.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Object> getTransactions(@PathVariable String accountNumber, @RequestParam String status) {
        try {
            List<StatusResponse> statusResponses = transactionService.getAllTransactionsCombined(accountNumber);
            return organizeTransactions(statusResponses, status);
        } catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Object> organizeTransactions(List<StatusResponse> statusResponses, String status) {
        List<StatusResponse> successTransactions = new ArrayList<>();
        List<StatusResponse> failureTransactions = new ArrayList<>();
        List<StatusResponse> pendingTransactions = new ArrayList<>();

        for (StatusResponse transaction : statusResponses) {
            if (SUCCESS.equalsIgnoreCase(transaction.getStatus())) {
                successTransactions.add(transaction);
            } else if (FAILURE.equalsIgnoreCase(transaction.getStatus())) {
                failureTransactions.add(transaction);
            } else if (PENDING.equalsIgnoreCase(transaction.getStatus())) {
                pendingTransactions.add(transaction);
            }
        }

        Map<String, List<StatusResponse>> organizedData = new HashMap<>();
        switch (status.toLowerCase()) {
            case ALL -> {
                organizedData.put(SUCCESS, successTransactions);
                organizedData.put(FAILURE, failureTransactions);
                organizedData.put(PENDING, pendingTransactions);
            }
            case SUCCESS -> organizedData.put(SUCCESS, successTransactions);
            case FAILURE -> organizedData.put(FAILURE, failureTransactions);
            case PENDING -> organizedData.put(PENDING, pendingTransactions);
            default -> {
                return new ResponseEntity<>(INVALID_TXN, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(organizedData, HttpStatus.OK);    }
}
