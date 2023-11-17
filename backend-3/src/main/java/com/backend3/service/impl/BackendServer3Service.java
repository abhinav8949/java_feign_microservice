package com.backend3.service.impl;

import com.backend3.model.Transaction;
import com.backend3.repository.BackendServer3TransactionRepository;
import com.backend3.service.IBackendServer3Service;
import com.mini_lib.response_DTO.BackendResponse;
import com.mini_lib.response_DTO.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BackendServer3Service implements IBackendServer3Service {
    @Autowired
    private BackendServer3TransactionRepository backendServer3TransactionRepository;

    @Override
    public void saveTransactions(Transaction transaction) {
        backendServer3TransactionRepository.save(transaction);
    }

    @Override
    public BackendResponse getPendingTransaction(String accountNumber, String status) {
        List<Transaction> transactions = backendServer3TransactionRepository.findByAccountNumberAndStatus(accountNumber, status);

        List<StatusResponse> statusResponses = transactions.stream()
                .map(txn -> {
                    return new StatusResponse(txn.getTransactionId(), txn.getStatus(), txn.getAmount(), txn.getDate());
                }).toList();
        return new BackendResponse(accountNumber, statusResponses);
    }
}
