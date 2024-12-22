package com.xCorporation.creditCardSystem.rest.controller;

import com.xCorporation.creditCardSystem.model.CardHolder;
import com.xCorporation.creditCardSystem.rest.converters.CardHolderConverter;
import com.xCorporation.creditCardSystem.rest.model.CardHolderRequest;
import com.xCorporation.creditCardSystem.rest.model.CardHolderResponse;
import com.xCorporation.creditCardSystem.service.CardHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card-holder")
public class CardHolderController {

    @Autowired
    private CardHolderService cardHolderService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public CardHolderResponse registerNewCardHolder(@RequestBody CardHolderRequest cardHolderRequest) {
        return CardHolderConverter.convert(cardHolderService.registerCardHolder(cardHolderRequest));
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CardHolderResponse updateCardHolder(@RequestBody CardHolderRequest cardHolderRequest, @PathVariable Long id) {
        return CardHolderConverter.convert(cardHolderService.modifyCardHolder(cardHolderRequest, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCardHolder(@PathVariable Long id) {
        cardHolderService.deleteCardHolder(id);
    }
}
