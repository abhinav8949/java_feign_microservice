package com.backend2.service;

import com.backend2.model.Transaction;
import com.mini_lib.response_DTO.BackendResponse;

import java.util.List;

public interface IBackendServer2Service {
    public void saveTransactions(Transaction transaction);
    public BackendResponse getFailureTransaction(String accountNumber, String status);

}
