package com.xCorporation.creditCardSystem.rest.controller;

import com.xCorporation.creditCardSystem.model.Transaction;
import com.xCorporation.creditCardSystem.rest.converters.TransactionConverter;
import com.xCorporation.creditCardSystem.rest.model.TransactionRateResponse;
import com.xCorporation.creditCardSystem.rest.model.TransactionRequest;
import com.xCorporation.creditCardSystem.rest.model.TransactionResponse;
import com.xCorporation.creditCardSystem.service.SimpleMailSenderService;
import com.xCorporation.creditCardSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private SimpleMailSenderService simpleMailSenderService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse makePurchase(@RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = transactionService.makePurchase(transactionRequest);
        simpleMailSenderService.sendTransactionEmail(transaction);
        return TransactionConverter.convert(transaction);
    }

    @GetMapping("/{transactionId}/rate")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public TransactionRateResponse getTransactionRateAndFee(@PathVariable("transactionId") Long transactionId) {
        return transactionService.getTransactionRateAndFee(transactionId);
    }
}
