package ru.exchange.rates.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExchangeRate {
    @NonNull
    private String currency;
    @NonNull
    private Double currencyPrice;

    public ExchangeRate(){}
}
