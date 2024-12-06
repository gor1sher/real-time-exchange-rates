package ru.exchange.rates.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.exchange.rates.dal.repository.CurrencyRepository;
import ru.exchange.rates.model.ExchangeRate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeService {

    private String token = System.getenv("token");
    private final RestTemplate restTemplate;

    @Autowired
    private CurrencyRepository currencyRepository;

    public Map<String, Double> exchangeRateList() {
        String url = String.format("%s?app_id=%s&base=%s",
                "https://openexchangerates.org/api/latest.json", token, "USD");

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response != null) {
            Map<String, Double> rates = (Map<String, Double>) response.get("rates");
            for (Map.Entry<String, Double> entry : rates.entrySet()) {
                currencyRepository.save(new ExchangeRate(entry.getKey(), entry.getValue()));
                return rates;
            }
        }
        return null;
    }

}
