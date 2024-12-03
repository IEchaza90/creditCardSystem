package com.xCorporation.creditCardSystem.service.strategy;

import java.time.LocalDate;
import java.util.Date;

public interface ServiceRateStrategy {

    double calculateServiceRate(LocalDate date);
}
