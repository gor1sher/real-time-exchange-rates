package ru.exchange.rates.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.exchange.rates.model.Currency;
import ru.exchange.rates.service.CurrencyExchangeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange-rate")
public class CurrencyController {

    private final CurrencyExchangeService currencyExchangeService;

    @GetMapping
    public Object getCurrency() {
        return currencyExchangeService.listCourse();
    }
}
