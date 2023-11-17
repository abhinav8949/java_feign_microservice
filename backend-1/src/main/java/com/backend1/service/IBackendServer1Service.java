package com.backend1.service;

import com.backend1.model.Transaction;
import com.mini_lib.response_DTO.BackendResponse;

import java.util.List;

public interface IBackendServer1Service {
    public void saveTransactions(Transaction transaction);
    public BackendResponse getSuccessTransaction(String accountNumber, String status);
}
