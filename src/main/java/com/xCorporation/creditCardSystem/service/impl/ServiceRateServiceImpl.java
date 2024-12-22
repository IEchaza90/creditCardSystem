package com.xCorporation.creditCardSystem.service.impl;

import com.xCorporation.creditCardSystem.model.Brand;
import com.xCorporation.creditCardSystem.service.ServiceRateService;
import com.xCorporation.creditCardSystem.service.strategy.AmexStrategy;
import com.xCorporation.creditCardSystem.service.strategy.NaraStrategy;
import com.xCorporation.creditCardSystem.service.strategy.ServiceRatesGateway;
import com.xCorporation.creditCardSystem.service.strategy.VisaStrategy;
import com.xCorporation.creditCardSystem.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@Service
public class ServiceRateServiceImpl implements ServiceRateService {

    @Autowired
    private ServiceRatesGateway gateway;

    @Autowired
    private VisaStrategy visaStrategy;

    @Autowired
    private NaraStrategy naraStrategy;

    @Autowired
    private AmexStrategy amexStrategy;

    @Override
    public double calculateServiceRate(LocalDate date, Brand brand) {
        switch (brand) {
            case VISA: gateway.setStrategy(visaStrategy); break;
            case NARA: gateway.setStrategy(naraStrategy); break;
            case AMEX: gateway.setStrategy(amexStrategy); break;
            default: throw new IllegalArgumentException("Invalid strategy");
        }
        date = date == null ? LocalDate.now() : date;
        return (double) Math.round(gateway.calculateServiceRate(date) * 100) / 100;
    }

    @Override
    public void calculateAllServiceRates() {
        Scanner scanner = new Scanner(System.in);
        LocalDate date;
        System.out.print("Please specify the date for which you want to know the service rates, empty date means today: ");
        String input = scanner.nextLine();
        boolean ok = false;
        while(!ok) {
            try {
                if (!input.isEmpty()) {
                    date = DateUtils.parseFromString(input);
                } else {
                    date = LocalDate.now();
                }

                for (Brand brand : Brand.values()) {
                    System.out.println(brand + ": " + calculateServiceRate(date, brand) + "%");
                }
                ok = true;

            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format, please try again: ");
                input = scanner.next();
            }
        }
    }
}
