package ru.exchange.rates.service;

import ru.exchange.rates.model.Currency;

import java.util.List;

public interface ExchangeRateService {

    public List<Currency> exchangeRateList();
    public Currency getExpensesCurrency(String currency);
}
