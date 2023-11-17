package com.mini_lib.response_DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StatusResponse {
    private Long transactionId;
    private String status;
    private String amount;
    private String date;
}
