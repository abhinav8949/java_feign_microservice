package com.backend1.service.impl;

import com.backend1.model.Transaction;
import com.backend1.repository.BackendServer1TransactionRepository;
import com.mini_lib.response_DTO.BackendResponse;
import com.mini_lib.response_DTO.StatusResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BackendServer1ServiceTest {

    @Mock
    private BackendServer1TransactionRepository backendServer1TransactionRepository;

    @InjectMocks
    private BackendServer1Service backendServer1Service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTransactions_shouldCallRepositorySave() {
        Transaction txn = new Transaction(1L, "success", "100.00", "2023-01-01", "111");
        backendServer1Service.saveTransactions(txn);
        verify(backendServer1TransactionRepository).save(txn);
    }

    @Test
    void getSuccessTransaction_shouldReturnBackendResponse() {
        String accountNumber = "123";
        String status = "success";
        List<Transaction> transactions = List.of(
                new Transaction(1L, status, "100.00", "2023-01-01", accountNumber),
                new Transaction(2L, status, "50.00", "2023-02-01", accountNumber)
        );
        BackendResponse expectedResponse = new BackendResponse(accountNumber, List.of(
                new StatusResponse(1L, status, "100.00", "2023-01-01"),
                new StatusResponse(2L, status, "50.00", "2023-02-01")
        ));

        when(backendServer1TransactionRepository.findByAccountNumberAndStatus(accountNumber, status)).thenReturn(transactions);
        BackendResponse result = backendServer1Service.getSuccessTransaction(accountNumber, status);
        assertEquals(expectedResponse, result);
    }
}
