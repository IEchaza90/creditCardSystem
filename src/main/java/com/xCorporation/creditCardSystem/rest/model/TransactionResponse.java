package com.xCorporation.creditCardSystem.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {

    private Long transactionId;

    private Double total;

    private String detail;

    private Long cardNumber;
}
