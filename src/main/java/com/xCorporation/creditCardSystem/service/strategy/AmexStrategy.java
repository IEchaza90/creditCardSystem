package com.xCorporation.creditCardSystem.service.strategy;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AmexStrategy implements ServiceRateStrategy{

    @Override
    public double calculateServiceRate(LocalDate date) {
        return date.getMonthValue() * 0.1;
    }
}
