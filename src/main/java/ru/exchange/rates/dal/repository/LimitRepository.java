package ru.exchange.rates.dal.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.exchange.rates.dal.mappers.LimitMappers;

import java.time.LocalDate;

@Repository
@AllArgsConstructor
public class LimitRepository {

    private final JdbcTemplate jdbc;
    private LimitMappers limitMappers;

    private static final String INSERT = "INSERT INTO limits (client_id, limit, localDate) " +
            "VALUES (?, ?, ?)";

    public void save(Long userId, Integer limit){
        jdbc.update(INSERT, userId, limit, LocalDate.now());
    }
}
