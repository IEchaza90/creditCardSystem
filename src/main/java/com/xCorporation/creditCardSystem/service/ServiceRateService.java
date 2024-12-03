package com.xCorporation.creditCardSystem.service;

import com.xCorporation.creditCardSystem.model.Brand;

import java.time.LocalDate;

public interface ServiceRateService {

    double calculateServiceRate(LocalDate date, Brand brand);

    void calculateAllServiceRates();
}
