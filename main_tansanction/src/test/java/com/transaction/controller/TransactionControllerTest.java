package com.transaction.controller;

import com.mini_lib.response_DTO.StatusResponse;
import com.transaction.service.impl.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
@ContextConfiguration(classes = {TransactionController.class})
@ExtendWith(SpringExtension.class)
class TransactionControllerTest {
    @MockBean
    private TransactionService transactionService;
    @Autowired
    private TransactionController transactionController;

    @Test
    void getTransactions_shouldReturnALLOrganizedData() {
        // Arrange
        String accountNumber = "123";
        String status = "all";

        List<StatusResponse> statusResponses = List.of(
                new StatusResponse(1L, "success", "100.00", "2023-01-01"),
                new StatusResponse(2L, "failure", "50.00", "2023-02-01"),
                new StatusResponse(3L, "pending", "75.00", "2023-03-01")
        );
        when(transactionService.getAllTransactionsCombined(accountNumber)).thenReturn(statusResponses);

        // Act
        ResponseEntity<Object> result = transactionController.getTransactions(accountNumber, status);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody() instanceof Map);
        Map<?, ?> resultMap = (Map<?, ?>) result.getBody();
        assertEquals(3, resultMap.size());
        assertTrue(resultMap.containsKey("success"));
        assertTrue(resultMap.containsKey("failure"));
        assertTrue(resultMap.containsKey("pending"));
    }

    @Test
    void getTransactions_shouldReturnOrganizedData_Success() {
        // Arrange
        String accountNumber = "123";
        String status = "success";

        List<StatusResponse> statusResponses = List.of(
                new StatusResponse(1L, "success", "100.00", "2023-01-01")
        );
        when(transactionService.getAllTransactionsCombined(accountNumber)).thenReturn(statusResponses);

        // Act
        ResponseEntity<Object> result = transactionController.getTransactions(accountNumber, status);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody() instanceof Map);
        Map<?, ?> resultMap = (Map<?, ?>) result.getBody();
        assertEquals(1, resultMap.size());
        assertTrue(resultMap.containsKey("success"));
    }

    @Test
    void getTransactions_shouldReturnALLOrganizedData_Failure() {
        // Arrange
        String accountNumber = "123";
        String status = "failure";

        List<StatusResponse> statusResponses = List.of(
                new StatusResponse(2L, "failure", "50.00", "2023-02-01")
        );
        when(transactionService.getAllTransactionsCombined(accountNumber)).thenReturn(statusResponses);

        // Act
        ResponseEntity<Object> result = transactionController.getTransactions(accountNumber, status);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody() instanceof Map);
        Map<?, ?> resultMap = (Map<?, ?>) result.getBody();
        assertEquals(1, resultMap.size());
        assertTrue(resultMap.containsKey("failure"));
    }

    @Test
    void getTransactions_shouldReturnALLOrganizedData_Pending() {
        // Arrange
        String accountNumber = "123";
        String status = "pending";

        List<StatusResponse> statusResponses = List.of(
                new StatusResponse(3L, "pending", "75.00", "2023-03-01")
        );
        when(transactionService.getAllTransactionsCombined(accountNumber)).thenReturn(statusResponses);

        // Act
        ResponseEntity<Object> result = transactionController.getTransactions(accountNumber, status);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody() instanceof Map);
        Map<?, ?> resultMap = (Map<?, ?>) result.getBody();
        assertEquals(1, resultMap.size());
        assertTrue(resultMap.containsKey("pending"));
    }

    @Test
    void getTransactions_shouldHandleException() {
        // Arrange
        String accountNumber = "123";
        String status = "ALL";
        when(transactionService.getAllTransactionsCombined(accountNumber)).thenThrow(new RuntimeException("Test exception"));

        // Act
        ResponseEntity<Object> result = transactionController.getTransactions(accountNumber, status);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals("Test exception", ((HashMap<?, ?>) Objects.requireNonNull(result.getBody())).get("errorMessage"));
    }
}
