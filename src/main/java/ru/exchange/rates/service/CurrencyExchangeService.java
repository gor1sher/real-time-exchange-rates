package ru.exchange.rates.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.exchange.rates.dal.repository.CurrencyRepository;
import ru.exchange.rates.exception.ConditionsNotMetException;
import ru.exchange.rates.model.Currency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeService implements ExchangeRateService{

    @Value("${api.token}")
    private  String token;

    @Value("${api.url}")
    private  String urlApi;

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    public List<Currency> listCourse() {
        return fetchExchangeRateAndReturn();
    }

    private List<Currency> retrieveExchangeRateFromAPI() {
        var url = String.format("%s?app_id=%s&base=%s",
                urlApi, token, "USD");

        var response = restTemplate.getForObject(url, Map.class);

        if (response == null) {
            throw new ConditionsNotMetException("нет внешних данных для обновления курса");
        }

        List<Currency> ratesList = new ArrayList<>();
        Map<String, Object> fetchRatesFromResponse = (Map<String, Object>) response.get("fetchRatesFromResponse");
        if (fetchRatesFromResponse == null) {
            throw new IllegalStateException("Response from API is null");
        }
        fetchRatesFromResponse.entrySet().forEach(entry -> {
            Object value = entry.getValue();
            Double rate = (value instanceof Integer v) ? (v).doubleValue() : (Double) value;
            ratesList.add(new Currency(entry.getKey(), rate, LocalDate.now()));
        });

        currencyRepository.saveAll(ratesList);
        return ratesList;
    }

    private List<Currency> fetchExchangeRateAndReturn() {
        List<Currency> list = currencyRepository.getDateOfTheExchangeRate(LocalDate.now());
        return list.isEmpty() ? retrieveExchangeRateFromAPI() : list;
    }
}
