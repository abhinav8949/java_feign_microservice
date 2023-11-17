package com.backend1.controller;

import com.backend1.service.impl.BackendServer1Service;
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
    private BackendServer1Service backendServer1Service;

    @InjectMocks
    private BackendServer1Controller backendServer1Controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllSuccessTransactions_shouldReturnSuccessResponse() {
        String accountNumber = "123";
        BackendResponse expectedResponse = mock(BackendResponse.class);
        when(backendServer1Service.getSuccessTransaction(accountNumber, "success")).thenReturn(expectedResponse);
        ResponseEntity<BackendResponse> result = backendServer1Controller.getAllSuccessTransactions(accountNumber);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedResponse, result.getBody());
    }
}
