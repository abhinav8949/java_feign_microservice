package com.mini_lib.response_DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class BackendResponse {
    private String accountNumber;
    private List<StatusResponse> responses;
}
