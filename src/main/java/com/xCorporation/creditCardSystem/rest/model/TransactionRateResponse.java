package com.xCorporation.creditCardSystem.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRateResponse {

    private Long transactionId;

    private Double cardRate;

    private String cardBrand;

    private Double totalFee;
}
