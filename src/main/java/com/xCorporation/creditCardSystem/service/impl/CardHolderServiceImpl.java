package com.xCorporation.creditCardSystem.service.impl;

import com.xCorporation.creditCardSystem.exception.CardHolderNotFoundException;
import com.xCorporation.creditCardSystem.model.CardHolder;
import com.xCorporation.creditCardSystem.repository.CardHolderRepository;
import com.xCorporation.creditCardSystem.rest.converters.CardHolderConverter;
import com.xCorporation.creditCardSystem.rest.model.CardHolderRequest;
import com.xCorporation.creditCardSystem.service.CardHolderService;
import com.xCorporation.creditCardSystem.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@Service
public class CardHolderServiceImpl implements CardHolderService {

    @Autowired
    public CardHolderRepository cardHolderRepository;

    @Override
    public void register() {
        Scanner scanner = new Scanner(System.in);
        CardHolder cardHolder = new CardHolder();

        System.out.println("Name: ");
        String input = scanner.next();
        cardHolder.setName(input);

        System.out.println("Surname: ");
        input = scanner.next();
        cardHolder.setSurname(input);

        System.out.println("Identity Document: ");
        input = scanner.next();
        cardHolder.setIdentityDocument(input);

        System.out.println("Email: ");
        input = scanner.next();

        //TODO it could contain a list of .edu/.gov/etc
        while (!input.contains("@") && !input.contains(".com")) {
            System.out.println("Please submit a valid email: ");
            input = scanner.next();
        }
        cardHolder.setEmail(input);

        System.out.println("Birthdate(dd-MM-YYYY): ");
        boolean ok = false;
        while (!ok) {
            try {
                input = scanner.next();
                cardHolder.setBirthDate(DateUtils.parseFromString(input));
                ok = true;

            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format, please try again: ");
            }
        }
        cardHolderRepository.save(cardHolder);
        System.out.println("Thank you for using our system");

    }

    @Override
    public CardHolder registerCardHolder(CardHolderRequest cardHolderRequest) {
        return cardHolderRepository.save(CardHolderConverter.convert(cardHolderRequest));
    }

    @Override
    public CardHolder modifyCardHolder(CardHolderRequest cardHolderRequest, Long id) {
        CardHolder cardHolder = cardHolderRepository.findById(id).orElseThrow(() -> new CardHolderNotFoundException(id));
        cardHolder.setBirthDate(cardHolderRequest.getBirthDate());
        cardHolder.setEmail(cardHolderRequest.getEmail());
        cardHolder.setName(cardHolderRequest.getName());
        cardHolder.setSurname(cardHolderRequest.getSurname());
        cardHolder.setIdentityDocument(cardHolderRequest.getIdentityDocument());
        cardHolderRepository.save(cardHolder);
        return cardHolder;
    }

    @Override
    public void deleteCardHolder(Long id) {
        cardHolderRepository.deleteById(id);
    }

    @Override
    public CardHolder findById(Long id) {
        return cardHolderRepository.findById(id).orElseThrow(() -> new CardHolderNotFoundException(id));
    }

    @Override
    public CardHolder findByNameAndSurname(String name, String surname) {
        return cardHolderRepository.findByNameAndSurname(name, surname);
    }
}
