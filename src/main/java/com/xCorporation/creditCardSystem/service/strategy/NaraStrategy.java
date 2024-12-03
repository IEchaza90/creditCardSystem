package com.xCorporation.creditCardSystem.service.strategy;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NaraStrategy implements ServiceRateStrategy{

    @Override
    public double calculateServiceRate(LocalDate date) {
        return date.getDayOfMonth() * 0.5;
    }
}
