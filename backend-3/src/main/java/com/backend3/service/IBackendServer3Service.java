package com.backend3.service;

import com.backend3.model.Transaction;
import com.mini_lib.response_DTO.BackendResponse;

import java.util.List;

public interface IBackendServer3Service {
    public void saveTransactions(Transaction transaction);
    public BackendResponse getPendingTransaction(String accountNumber, String status);

}
