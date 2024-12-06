package ru.exchange.rates.dal.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.exchange.rates.model.ExchangeRate;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CurrencyMappers implements RowMapper<ExchangeRate> {

    @Override
    public ExchangeRate mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExchangeRate exchangeRate = new ExchangeRate();

        exchangeRate.setCurrency(rs.getString("name"));
        exchangeRate.setCurrencyPrice(rs.getDouble("priceUSD"));

        return exchangeRate;
    }
}
