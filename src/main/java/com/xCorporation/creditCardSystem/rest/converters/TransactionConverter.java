package com.xCorporation.creditCardSystem.rest.converters;

import com.xCorporation.creditCardSystem.model.Transaction;
import com.xCorporation.creditCardSystem.rest.model.TransactionRequest;
import com.xCorporation.creditCardSystem.rest.model.TransactionResponse;

import java.time.LocalDate;

public class TransactionConverter {

    public static Transaction convert(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setSubtotal(transactionRequest.getAmount());
        transaction.setDetail(transactionRequest.getDetail());
        transaction.setTransactionDate(LocalDate.now());
        return transaction;
    }

    public static TransactionResponse convert(Transaction transaction) {
        return TransactionResponse.builder()
                .transactionId(transaction.getTransactionId())
                .cardNumber(transaction.getCard().getCardNumber())
                .detail(transaction.getDetail())
                .total(transaction.getTotal())
                .build();
    }
}
