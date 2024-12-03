package com.xCorporation.creditCardSystem.service.strategy;

import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Setter
@Component
public class ServiceRatesGateway {

    private ServiceRateStrategy strategy;

    public double calculateServiceRate(LocalDate date) {
        return strategy.calculateServiceRate(date);
    }

}
