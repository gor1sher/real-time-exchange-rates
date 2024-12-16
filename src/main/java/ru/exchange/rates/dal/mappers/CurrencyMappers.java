package ru.exchange.rates.dal.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.exchange.rates.model.Currency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class CurrencyMappers implements RowMapper<Currency> {

    @Override
    public Currency mapRow(ResultSet rs, int rowNum) throws SQLException {
        Currency exchangeRate = new Currency();

        exchangeRate.setName(rs.getString("name"));
        exchangeRate.setPriceInUSD(rs.getDouble("priceUSD"));
        Timestamp timestamp = rs.getTimestamp("date_of_the_exchange_rate");
        exchangeRate.setDateOfCourseUpdate(timestamp.toLocalDateTime().toLocalDate());

        return exchangeRate;
    }
}
