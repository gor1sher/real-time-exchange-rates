package ru.exchange.rates;

import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.exchange.rates.controller.CurrencyController;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RequiredArgsConstructor
public class CurrencyControllerTest {

    @Autowired
    private CurrencyController currencyController;

    @Test
    public void TesGetCurrency() {
        Map<String, Double> rates = currencyController.getCurrency();
        assertNotNull(rates); // Убедитесь, что результат не null
        System.out.println(rates);
    }
}
