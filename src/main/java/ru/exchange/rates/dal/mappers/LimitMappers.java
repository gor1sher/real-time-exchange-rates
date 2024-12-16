package ru.exchange.rates.dal.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.exchange.rates.model.LimitModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class LimitMappers implements RowMapper<LimitModel> {

    @Override
    public LimitModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        LimitModel limitModel = new LimitModel();

        limitModel.setUserId(rs.getLong("client_id"));
        limitModel.setLimit(rs.getInt("limit"));
        Timestamp timestamp = rs.getTimestamp("localDate");
        limitModel.setLocalDate(timestamp.toLocalDateTime().toLocalDate());

        return limitModel;
    }
}
