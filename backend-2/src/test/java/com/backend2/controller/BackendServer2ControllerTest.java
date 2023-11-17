package com.backend2.controller;

import com.backend2.service.impl.BackendServer2Service;
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

class BackendServer2ControllerTest {

    @Mock
    private BackendServer2Service backendServer2Service;

    @InjectMocks
    private BackendServer2Controller backendServer2Controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFailureTransactions_shouldReturnFailResponse() {
        String accountNumber = "123";
        BackendResponse expectedResponse = mock(BackendResponse.class);
        when(backendServer2Service.getFailureTransaction(accountNumber, "failure")).thenReturn(expectedResponse);
        ResponseEntity<BackendResponse> result = backendServer2Controller.getAllFailureTransactions(accountNumber);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedResponse, result.getBody());
    }
}
