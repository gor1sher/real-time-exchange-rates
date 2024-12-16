package ru.exchange.rates.service;

import ru.exchange.rates.model.Currency;

import java.util.List;

public interface ExchangeRateService {

    List<Currency> listCourse();
}
