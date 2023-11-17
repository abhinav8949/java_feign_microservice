package com.backend2.controller;

import com.backend2.service.impl.BackendServer2Service;
import com.mini_lib.response_DTO.BackendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/backendserver2/failure")
public class BackendServer2Controller {
    @Autowired
    private BackendServer2Service backendServer2Service;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<BackendResponse> getAllFailureTransactions(@PathVariable String accountNumber) {
        return new ResponseEntity<>(backendServer2Service.getFailureTransaction(accountNumber, "failure"), HttpStatus.OK);
    }
}
