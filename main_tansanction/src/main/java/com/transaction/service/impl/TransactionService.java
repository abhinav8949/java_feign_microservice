package com.transaction.service.impl;

import com.mini_lib.response_DTO.BackendResponse;
import com.mini_lib.response_DTO.StatusResponse;
import com.transaction.client.BackendServer1Client;
import com.transaction.client.BackendServer2Client;
import com.transaction.client.BackendServer3Client;
import com.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.transaction.utility.Constants.*;

@Service
public class TransactionService implements ITransactionService {

    private final BackendServer1Client backendServer1Client;
    private final BackendServer2Client backendServer2Client;
    private final BackendServer3Client backendServer3Client;

    @Autowired
    public TransactionService(
            BackendServer1Client backendServer1Client,
            BackendServer2Client backendServer2Client,
            BackendServer3Client backendServer3Client
    ) {
        this.backendServer1Client = backendServer1Client;
        this.backendServer2Client = backendServer2Client;
        this.backendServer3Client = backendServer3Client;
    }

    @Override
    public List<StatusResponse> getAllTransactionsCombined(String accountNumber) {
        CompletableFuture<List<StatusResponse>> backend1Response = fetchAndMapToKey(backendServer1Client.getSuccessTransactions(accountNumber), SUCCESS);
        CompletableFuture<List<StatusResponse>> backend2Response = fetchAndMapToKey(backendServer2Client.getFailureTransactions(accountNumber), FAILURE);
        CompletableFuture<List<StatusResponse>> backend3Response = fetchAndMapToKey(backendServer3Client.getPendingTransactions(accountNumber), PENDING);

        CompletableFuture<Void> allOf = CompletableFuture.allOf(backend1Response, backend2Response, backend3Response);

        CompletableFuture<List<StatusResponse>> combinedFuture = allOf.thenApply(
                v -> Stream.of(backend1Response, backend2Response, backend3Response)
                        .map(CompletableFuture::join)
                        .flatMap(List::stream)
                        .collect(Collectors.toList())
        );
        return combinedFuture.join();
    }

    private CompletableFuture<List<StatusResponse>> fetchAndMapToKey(BackendResponse backendResponse, String key) {
        return CompletableFuture.supplyAsync(() -> {
            if (backendResponse != null) {
                return backendResponse.getResponses().stream()
                        .peek(response -> response.setStatus(key))
                        .toList();
            } else {
                return List.of();
            }
        });
    }
}
