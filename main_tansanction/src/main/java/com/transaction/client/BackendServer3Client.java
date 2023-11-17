package com.transaction.client;

import com.mini_lib.response_DTO.BackendResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "backendServer3", url = "http://localhost:8083")
public interface BackendServer3Client {
    @GetMapping("/backendserver3/pending/{accountNumber}")
    BackendResponse getPendingTransactions(@PathVariable String accountNumber);

}
