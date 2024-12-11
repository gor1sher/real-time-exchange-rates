package ru.exchange.rates.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.exchange.rates.model.Currency;
import ru.exchange.rates.service.CurrencyExchangeService;

@RestController
@RequestMapping("/exchange-rate")
public class CurrencyController {

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @GetMapping
    public Object getCurrency() {
        return currencyExchangeService.exchangeRateList();
    }

    @GetMapping("/{currency}")
    public Currency getExpensesInTheDesiredCurrency(@PathVariable (name = "currency") String currency){
        return currencyExchangeService.getExpensesCurrency(currency);
    }
}
