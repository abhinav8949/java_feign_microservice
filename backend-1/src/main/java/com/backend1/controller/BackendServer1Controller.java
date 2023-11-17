package com.backend1.controller;

import com.backend1.service.impl.BackendServer1Service;
import com.mini_lib.response_DTO.BackendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backendserver1/success")
public class BackendServer1Controller {
    @Autowired
    private BackendServer1Service backendServer1Service;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<BackendResponse> getAllSuccessTransactions(@PathVariable String accountNumber){
        return new ResponseEntity<>(backendServer1Service.getSuccessTransaction(accountNumber,"success"), HttpStatus.OK);
    }
}
