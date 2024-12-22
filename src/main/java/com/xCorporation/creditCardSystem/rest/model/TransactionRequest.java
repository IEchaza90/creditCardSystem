package com.xCorporation.creditCardSystem.rest.model;

import lombok.Data;

@Data
public class TransactionRequest {

    private Double amount;

    private String detail;

    private Long cardNumber;

    private Long cvv;
}
