package com.transaction.service;

import com.mini_lib.response_DTO.StatusResponse;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface ITransactionService {
    @Async
    List<StatusResponse> getAllTransactionsCombined(String accountNumber);
}
