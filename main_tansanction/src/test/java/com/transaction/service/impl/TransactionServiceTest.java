package com.transaction.service.impl;

import com.mini_lib.response_DTO.StatusResponse;
import com.transaction.client.BackendServer1Client;
import com.transaction.client.BackendServer2Client;
import com.transaction.client.BackendServer3Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TransactionService.class})
@ExtendWith(SpringExtension.class)
class TransactionServiceTest {
    @MockBean
    private BackendServer1Client backendServer1Client;
    @MockBean
    private BackendServer2Client backendServer2Client;
    @MockBean
    private BackendServer3Client backendServer3Client;
    @Autowired
    private TransactionService transactionService;

    @Test
    void getAllTransactionsCombined_shouldReturnCombinedList() {
        String accountNumber = "123";
        when(backendServer1Client.getSuccessTransactions(accountNumber)).thenReturn(Mockito.any());
        when(backendServer2Client.getFailureTransactions(accountNumber)).thenReturn(Mockito.any());
        when(backendServer3Client.getPendingTransactions(accountNumber)).thenReturn(Mockito.any());
        List<StatusResponse> result = transactionService.getAllTransactionsCombined(accountNumber);
        assertEquals(0, result.size());
    }
}
