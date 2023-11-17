package com.transaction.client;

import com.mini_lib.response_DTO.BackendResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "backendServer2", url = "http://localhost:8082")
public interface BackendServer2Client {
    @GetMapping("/backendserver2/failure/{accountNumber}")
    BackendResponse getFailureTransactions(@PathVariable String accountNumber);
}
