package com.xCorporation.creditCardSystem;

import com.xCorporation.creditCardSystem.service.CardHolderService;
import com.xCorporation.creditCardSystem.service.CardService;
import com.xCorporation.creditCardSystem.service.ServiceRateService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    @Autowired
    private CardHolderService cardHolderService;

    @Autowired
    private CardService cardService;

    @Autowired
    private ServiceRateService serviceRateService;

    //@PostConstruct
    public void display() {
        Scanner scanner = new Scanner(System.in);
        char opcion;
        String mensajeOpciones = "Welcome to Corporation X Credit Card Payment System !\nPlease select an option:\n" +
                "1) Register new Card Holder\n2) Register new Card\n3) Search cards by identity document number\n" +
                "4) Calculate service rates for a given date\n5) Exit";

        while (true) {
            System.out.println(mensajeOpciones);
            opcion = scanner.next().charAt(0);
            switch (opcion) {
                case '1': cardHolderService.register(); break;
                case '2': cardService.register(); break;
                case '3': cardService.findCardsByCardHolderIdentityDocument(); break;
                case '4': serviceRateService.calculateAllServiceRates(); break;
                case '5': System.out.println("See you soon !"); System.exit(0);
                default: System.out.println("Invalid Option");
            }
        }
    }
}
