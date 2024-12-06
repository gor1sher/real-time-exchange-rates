package ru.exchange.rates.dal.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.exchange.rates.dal.mappers.CurrencyMappers;
import ru.exchange.rates.model.ExchangeRate;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CurrencyRepository {

    private final JdbcTemplate jdbc;
    @Autowired
    private CurrencyMappers currencyMappers;

    private static final String FIND_ALL = "SELECT * FROM currency";
    private static final String INSERT = "INSERT INTO currency(name, priceUSD) " +
            "VALUES(?, ?)";

    public List<ExchangeRate> getListExchangeRates() {
        return jdbc.query(FIND_ALL, currencyMappers);
    }

    public void save(ExchangeRate exchangeRate) {
        jdbc.update(INSERT, exchangeRate.getCurrency(), exchangeRate.getCurrencyPrice());
    }

}
