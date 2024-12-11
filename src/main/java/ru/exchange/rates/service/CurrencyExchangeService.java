package ru.exchange.rates.service;

import lombok.NonNull;
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
    private String token;

    @Value("${api.url}")
    private String urlApi;

    @NonNull
    private final RestTemplate restTemplate;

    @NonNull
    private CurrencyRepository currencyRepository;

    public List<Currency> exchangeRateList() {
        return checkDataForCurrency();
    }

    public Currency getExpensesCurrency(String currency) {
        return null;
    }

    private List<Currency> updateExchangeRate() {
        String url = String.format("%s?app_id=%s&base=%s",
                urlApi, token, "USD");

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null) {
            throw new ConditionsNotMetException("нет внешних данных для обновления курса");
        }

        List<Currency> rs = new ArrayList<>();
        Map<String, Object> rates = (Map<String, Object>) response.get("rates");
        for (Map.Entry<String, Object> entry : rates.entrySet()) {
            Object value = entry.getValue();
            Double rate = (value instanceof Integer) ? ((Integer) value).doubleValue() : (Double) value;

            rs.add(new Currency(entry.getKey(), rate, LocalDate.now()));

            currencyRepository.save(new Currency(entry.getKey(), rate, LocalDate.now()));
        }
        return rs;
    }

    //если данные по курсам свежие, то будут браться из бд
    private List<Currency> checkDataForCurrency() {
        List<Currency> list = currencyRepository.getDateOfTheExchangeRate(LocalDate.now());
        if (list.isEmpty()) {
            return updateExchangeRate();
        } else {
            return list;
        }
    }
}
