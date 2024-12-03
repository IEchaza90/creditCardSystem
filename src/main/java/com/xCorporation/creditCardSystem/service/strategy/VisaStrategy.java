package com.xCorporation.creditCardSystem.service.strategy;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class VisaStrategy implements ServiceRateStrategy {

    @Override
    public double calculateServiceRate(LocalDate date) {
        return  ((double) date.getYear() - 2000) / date.getMonthValue();
    }
}
