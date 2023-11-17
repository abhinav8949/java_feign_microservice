package com.backend3.controller;

import com.backend3.service.impl.BackendServer3Service;
import com.mini_lib.response_DTO.BackendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/backendserver3/pending/")
public class BackendServer3Controller {
    @Autowired
    private BackendServer3Service backendServer1Service;

    @GetMapping("{accountNumber}")
    public ResponseEntity<BackendResponse> getAllPendingTransactions(@PathVariable String accountNumber) {
        return new ResponseEntity<>(backendServer1Service.getPendingTransaction(accountNumber, "pending"), HttpStatus.OK);
    }
}
