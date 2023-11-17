package com.backend1.service.impl;

import com.backend1.model.Transaction;
import com.backend1.repository.BackendServer1TransactionRepository;
import com.backend1.service.IBackendServer1Service;
import com.mini_lib.response_DTO.BackendResponse;
import com.mini_lib.response_DTO.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackendServer1Service implements IBackendServer1Service {
    @Autowired
    private BackendServer1TransactionRepository backendServer1TransactionRepository;

    @Override
    public void saveTransactions(Transaction transaction) {
        backendServer1TransactionRepository.save(transaction);
    }

    @Override
    public BackendResponse getSuccessTransaction(String accountNumber, String status) {
        List<Transaction> transactions = backendServer1TransactionRepository.findByAccountNumberAndStatus(accountNumber, status);
        List<StatusResponse> statusResponses = transactions.stream()
                .map(txn -> {
                    return new StatusResponse(txn.getTransactionId(), txn.getStatus(), txn.getAmount(), txn.getDate());
                }).toList();
        return new BackendResponse(accountNumber, statusResponses);
    }
}
