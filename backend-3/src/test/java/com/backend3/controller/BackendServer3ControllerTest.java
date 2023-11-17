package com.backend3.controller;

import com.backend3.service.impl.BackendServer3Service;
import com.mini_lib.response_DTO.BackendResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BackendServer1ControllerTest {
    @Mock
    private BackendServer3Service backendServer3Service;
    @InjectMocks
    private BackendServer3Controller backendServer3Controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllSuccessTransactions_shouldReturnSuccessResponse() {
        String accountNumber = "123";
        BackendResponse expectedResponse = mock(BackendResponse.class);
        when(backendServer3Service.getPendingTransaction(accountNumber, "pending")).thenReturn(expectedResponse);
        ResponseEntity<BackendResponse> result = backendServer3Controller.getAllPendingTransactions(accountNumber);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedResponse, result.getBody());
    }
}
