package ru.exchange.rates.dal.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.exchange.rates.dal.mappers.CurrencyMappers;
import ru.exchange.rates.model.Currency;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class CurrencyRepository {

    private final JdbcTemplate jdbc;
    private CurrencyMappers currencyMappers;

    private static final String FIND_ALL = "SELECT * FROM currency";
    private static final String FIND_BY_CURRENCY = "SELECT * FROM currency WHERE name = ?";
    private static final String FIND_BY_DATE = "SELECT * FROM currency WHERE date_of_the_exchange_rate = ?";
    private static final String INSERT = "MERGE INTO currency(name, priceUSD, date_of_the_exchange_rate) " +
            "KEY(name) VALUES(?, ?, ?)";



    public List<Currency> getListExchangeRates() {
        return jdbc.query(FIND_ALL, currencyMappers);
    }

    public Currency getCurrency(String currency) {
        return jdbc.queryForObject(FIND_BY_CURRENCY, currencyMappers, currency);
    }

    public List<Currency> getDateOfTheExchangeRate(LocalDate localDate) {
        return jdbc.query(FIND_BY_DATE, currencyMappers, localDate);
    }

    public void save(Currency exchangeRate) {
        jdbc.update(INSERT, exchangeRate.getName(), exchangeRate.getPriceByUSD(), exchangeRate.getLocalDate());
    }
}
