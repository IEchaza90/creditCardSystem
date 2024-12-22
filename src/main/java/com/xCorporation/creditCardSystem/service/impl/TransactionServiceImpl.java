package com.xCorporation.creditCardSystem.service.impl;

import com.xCorporation.creditCardSystem.exception.CardNotFoundException;
import com.xCorporation.creditCardSystem.exception.InvalidTransactionException;
import com.xCorporation.creditCardSystem.exception.TransactionNotFoundException;
import com.xCorporation.creditCardSystem.model.Brand;
import com.xCorporation.creditCardSystem.model.Card;
import com.xCorporation.creditCardSystem.model.Transaction;
import com.xCorporation.creditCardSystem.repository.CardRepository;
import com.xCorporation.creditCardSystem.repository.TransactionRepository;
import com.xCorporation.creditCardSystem.rest.converters.TransactionConverter;
import com.xCorporation.creditCardSystem.rest.model.TransactionRateResponse;
import com.xCorporation.creditCardSystem.rest.model.TransactionRequest;
import com.xCorporation.creditCardSystem.service.ServiceRateService;
import com.xCorporation.creditCardSystem.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final CardRepository cardRepository;

    private final ServiceRateService serviceRateService;

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(CardRepository cardRepository, ServiceRateService serviceRateService, TransactionRepository transactionRepository) {
        this.cardRepository = cardRepository;
        this.serviceRateService = serviceRateService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction makePurchase(TransactionRequest transactionRequest) {
        if(transactionRequest.getAmount() >= 10000) {
            throw new InvalidTransactionException("Your purchase is over $10000");
        }
        Card card = cardRepository.findByCardNumber(transactionRequest.getCardNumber())
                .orElseThrow(() -> new CardNotFoundException("Card not found with number " + transactionRequest.getCardNumber()));
        if(card.getCvv() != transactionRequest.getCvv()) {
            throw new InvalidTransactionException("Your card information is invalid");
        }
        if(!card.getExpirationDate().isAfter(LocalDate.now())) {
            throw new InvalidTransactionException("Your card is expired");
        }
        Double serviceRate = serviceRateService.calculateServiceRate(LocalDate.now(), Brand.valueOf(card.getBrand().toUpperCase()));
        Double serviceTotal = (transactionRequest.getAmount() * serviceRate) / 100;
        Double purchaseTotal = transactionRequest.getAmount() + serviceTotal;
        Transaction transaction = TransactionConverter.convert(transactionRequest);
        transaction.setCard(card);
        transaction.setComisionRate(serviceRate);
        transaction.setTotal(purchaseTotal);
        return transactionRepository.save(transaction);
    }

    @Override
    public TransactionRateResponse getTransactionRateAndFee(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() -> new TransactionNotFoundException(transactionId));
        return TransactionRateResponse.builder()
                .transactionId(transaction.getTransactionId())
                .cardBrand(String.valueOf(transaction.getCard().getBrand()))
                .cardRate(transaction.getComisionRate())
                .totalFee(transaction.getTotal() - transaction.getSubtotal())
                .build();
    }
}
