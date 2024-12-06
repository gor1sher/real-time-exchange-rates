package ru.exchange.rates.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.exchange.rates.service.CurrencyExchangeService;

import java.util.Map;

@RestController
@RequestMapping("/exchange-rate")
public class CurrencyController {

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @GetMapping
    public Map<String, Double> getCurrency() {
        return currencyExchangeService.exchangeRateList();
    }
}
