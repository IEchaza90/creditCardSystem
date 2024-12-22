package com.xCorporation.creditCardSystem.service;

import com.xCorporation.creditCardSystem.model.Transaction;
import com.xCorporation.creditCardSystem.rest.model.TransactionRateResponse;
import com.xCorporation.creditCardSystem.rest.model.TransactionRequest;

public interface TransactionService {

    Transaction makePurchase(TransactionRequest transactionRequest);

    TransactionRateResponse getTransactionRateAndFee(Long transactionId);
}
