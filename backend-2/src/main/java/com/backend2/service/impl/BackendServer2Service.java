package com.backend2.service.impl;

import com.backend2.model.Transaction;
import com.backend2.repository.BackendServer2TransactionRepository;
import com.backend2.service.IBackendServer2Service;
import com.mini_lib.response_DTO.BackendResponse;
import com.mini_lib.response_DTO.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BackendServer2Service implements IBackendServer2Service {
    @Autowired
    private BackendServer2TransactionRepository backendServer2TransactionRepository;

    @Override
    public void saveTransactions(Transaction transaction) {
        backendServer2TransactionRepository.save(transaction);
    }

    @Override
    public BackendResponse getFailureTransaction(String accountNumber, String status) {
        List<Transaction> transactions = backendServer2TransactionRepository.findByAccountNumberAndStatus(accountNumber, status);

        List<StatusResponse> statusResponses = transactions.stream()
                .map(txn -> {
                    return new StatusResponse(txn.getTransactionId(), txn.getStatus(), txn.getAmount(), txn.getDate());
                }).toList();

        return new BackendResponse(accountNumber, statusResponses);

    }
}
