package com.xCorporation.creditCardSystem.service.impl;

import com.xCorporation.creditCardSystem.exception.CardHolderNotFoundException;
import com.xCorporation.creditCardSystem.exception.CardNotFoundException;
import com.xCorporation.creditCardSystem.rest.converters.CardConverter;
import com.xCorporation.creditCardSystem.rest.converters.PrivateCardViewConverter;
import com.xCorporation.creditCardSystem.model.Brand;
import com.xCorporation.creditCardSystem.model.Card;
import com.xCorporation.creditCardSystem.model.CardHolder;
import com.xCorporation.creditCardSystem.rest.model.CardRequest;
import com.xCorporation.creditCardSystem.rest.model.CardResponse;
import com.xCorporation.creditCardSystem.repository.CardHolderRepository;
import com.xCorporation.creditCardSystem.repository.CardRepository;
import com.xCorporation.creditCardSystem.service.CardHolderService;
import com.xCorporation.creditCardSystem.service.CardService;
import com.xCorporation.creditCardSystem.service.SimpleMailSenderService;
import com.xCorporation.creditCardSystem.utils.DateUtils;
import com.xCorporation.creditCardSystem.utils.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardHolderService cardHolderService;

    @Override
    public void register() {
        Scanner scanner = new Scanner(System.in);
        Card card = new Card();

        System.out.println("Brand(VISA/NARA/AMEX): ");
        String input = scanner.next();
        boolean ok = false;
        while (!ok) {
            try {
                Brand brand = Brand.valueOf(input.toUpperCase());
                card.setBrand(brand.toString());
                ok = true;

            } catch (IllegalArgumentException e) {
                System.out.println("Please provide one of the valid brands(VISA/NARA/AMEX): ");
                input = scanner.next();
            }
        }

        System.out.println("Card Number: ");
        Long number = scanner.nextLong();
        card.setCardNumber(number);

        System.out.println("Expiration Date(dd-MM-yyyy): ");
        ok = false;
        while (!ok) {
            try {
                input = scanner.next();
                card.setExpirationDate(DateUtils.parseFromString(input));
                ok = true;

            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format, please try again: ");
            }
        }
        ok = false;
        while (!ok) {
            System.out.println("Name of the card holder: ");
            String name = scanner.next();

            System.out.println("Surname of the card holder: ");
            String surname = scanner.next();

            CardHolder cardHolder = cardHolderService.findByNameAndSurname(name, surname);
            if (cardHolder == null) {
                System.out.println("User not found");
            } else {
                ok = true;
            }

            card.setCardHolder(cardHolder);
        }

        card.setCvv(GeneralUtils.generateRandomCvv());
        cardRepository.save(card);
        System.out.println("Thank you for using our system");

    }

    @Override
    public void findCardsByCardHolderIdentityDocument() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert de identity document for the card holder you wish to seek: ");
        String input = scanner.next();
        List<CardResponse> cardResponses =  PrivateCardViewConverter.convert(cardRepository.findAllByCardHolderIdentityDocument(input));
        System.out.println(!cardRepository.findAllByCardHolderIdentityDocument(input).isEmpty()
                ? PrivateCardViewConverter.convert(cardRepository.findAllByCardHolderIdentityDocument(input))
                : "The user has no cards registered");
    }

    @Override
    public Card registerCard(CardRequest cardRequest) {
        CardHolder cardHolder =  cardHolderService.findById(cardRequest.getCardHolderId());
        Card card = CardConverter.convert(cardRequest, cardHolder);
        card.setCvv(GeneralUtils.generateRandomCvv());
        return cardRepository.save(card);
    }

    @Override
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
