package com.backend3.service.impl;

import com.backend3.model.Transaction;
import com.backend3.repository.BackendServer3TransactionRepository;
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
    private BackendServer3TransactionRepository backendServer3TransactionRepository;

    @InjectMocks
    private BackendServer3Service backendServer3Service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTransactions_shouldCallRepositorySave() {
        Transaction txn = new Transaction(1L, "success", "100.00", "2023-01-01", "111");
        backendServer3Service.saveTransactions(txn);
        verify(backendServer3TransactionRepository).save(txn);
    }

    @Test
    void getSuccessTransaction_shouldReturnBackendResponse() {
        String accountNumber = "123";
        String status = "pending";
        List<Transaction> transactions = List.of(
                new Transaction(1L, status, "100.00", "2023-01-01", accountNumber),
                new Transaction(2L, status, "50.00", "2023-02-01", accountNumber)
        );
        BackendResponse expectedResponse = new BackendResponse(accountNumber, List.of(
                new StatusResponse(1L, status, "100.00", "2023-01-01"),
                new StatusResponse(2L, status, "50.00", "2023-02-01")
        ));

        when(backendServer3TransactionRepository.findByAccountNumberAndStatus(accountNumber, status)).thenReturn(transactions);
        BackendResponse result = backendServer3Service.getPendingTransaction(accountNumber, status);
        assertEquals(expectedResponse, result);
    }
}
