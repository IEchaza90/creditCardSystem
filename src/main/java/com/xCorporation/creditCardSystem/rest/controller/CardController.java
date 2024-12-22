package com.xCorporation.creditCardSystem.rest.controller;

import com.xCorporation.creditCardSystem.model.Card;
import com.xCorporation.creditCardSystem.rest.converters.CardConverter;
import com.xCorporation.creditCardSystem.rest.model.CardRequest;
import com.xCorporation.creditCardSystem.rest.model.CardResponse;
import com.xCorporation.creditCardSystem.service.CardService;
import com.xCorporation.creditCardSystem.service.SimpleMailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private SimpleMailSenderService simpleMailSenderService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponse registerNewCard(@RequestBody CardRequest cardRequest) {
        Card card = cardService.registerCard(cardRequest);
        simpleMailSenderService.sendCardMail(card);
        return CardConverter.convert(card);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}
