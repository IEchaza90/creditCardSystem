package com.xCorporation.creditCardSystem.service.impl;

import com.xCorporation.creditCardSystem.converters.PrivateCardViewConverter;
import com.xCorporation.creditCardSystem.model.Brand;
import com.xCorporation.creditCardSystem.model.Card;
import com.xCorporation.creditCardSystem.model.CardHolder;
import com.xCorporation.creditCardSystem.model.PrivateCard;
import com.xCorporation.creditCardSystem.repository.CardHolderRepository;
import com.xCorporation.creditCardSystem.repository.CardRepository;
import com.xCorporation.creditCardSystem.service.CardService;
import com.xCorporation.creditCardSystem.utils.DateUtils;
import com.xCorporation.creditCardSystem.utils.MenuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardHolderRepository cardHolderRepository;

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

            CardHolder cardHolder = cardHolderRepository.findByNameAndSurname(name, surname);
            if (cardHolder == null) {
                System.out.println("User not found");
            } else {
                ok = true;
            }

            card.setCardHolder(cardHolder);
        }

        card.setCvv(MenuUtils.generateRandomCvv());
        cardRepository.save(card);
        System.out.println("Thank you for using our system");

    }

    @Override
    public void findCardsByCardHolderIdentityDocument() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert de identity document for the card holder you wish to seek: ");
        String input = scanner.next();
        List<PrivateCard> privateCards =  PrivateCardViewConverter.convert(cardRepository.findAllByCardHolderIdentityDocument(input));
        System.out.println(!cardRepository.findAllByCardHolderIdentityDocument(input).isEmpty()
                ? PrivateCardViewConverter.convert(cardRepository.findAllByCardHolderIdentityDocument(input))
                : "The user has no cards registered");
    }
}
