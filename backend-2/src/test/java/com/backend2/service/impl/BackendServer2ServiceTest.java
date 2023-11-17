package com.backend2.service.impl;

import com.backend2.model.Transaction;
import com.backend2.repository.BackendServer2TransactionRepository;
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
    private BackendServer2TransactionRepository backendServer2TransactionRepository;

    @InjectMocks
    private BackendServer2Service backendServer2Service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTransactions_shouldCallRepositorySave() {
        Transaction txn = new Transaction(1L, "success", "100.00", "2023-01-01", "111");
        backendServer2Service.saveTransactions(txn);
        verify(backendServer2TransactionRepository).save(txn);
    }

    @Test
    void getSuccessTransaction_shouldReturnBackendResponse() {
        String accountNumber = "123";
        String status = "failure";
        List<Transaction> transactions = List.of(
                new Transaction(1L, status, "100.00", "2023-01-01", accountNumber),
                new Transaction(2L, status, "50.00", "2023-02-01", accountNumber)
        );
        BackendResponse expectedResponse = new BackendResponse(accountNumber, List.of(
                new StatusResponse(1L, status, "100.00", "2023-01-01"),
                new StatusResponse(2L, status, "50.00", "2023-02-01")
        ));

        when(backendServer2TransactionRepository.findByAccountNumberAndStatus(accountNumber, status)).thenReturn(transactions);
        BackendResponse result = backendServer2Service.getFailureTransaction(accountNumber, status);
        assertEquals(expectedResponse, result);
    }
}
