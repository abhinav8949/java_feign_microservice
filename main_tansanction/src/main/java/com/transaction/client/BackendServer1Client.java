package com.transaction.client;

import com.mini_lib.response_DTO.BackendResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "backendServer1", url = "http://localhost:8081")
public interface BackendServer1Client {
    @GetMapping("/backendserver1/success/{accountNumber}")
    BackendResponse getSuccessTransactions(@PathVariable String accountNumber);
}
