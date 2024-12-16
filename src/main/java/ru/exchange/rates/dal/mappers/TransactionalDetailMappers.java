package ru.exchange.rates.dal.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.exchange.rates.model.TransactionDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class TransactionalDetailMappers implements RowMapper<TransactionDetail> {

    @Override
    public TransactionDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransactionDetail transactionDetail = new TransactionDetail();

        transactionDetail.setUserId(rs.getLong("client_id"));
        transactionDetail.setExpense(rs.getDouble("expense"));
        transactionDetail.setLimit(rs.getDouble("expense_limit"));
        transactionDetail.setLimitExceeded(rs.getBoolean("limit_exceeded"));
        Timestamp timestamp = rs.getTimestamp("localDate");
        transactionDetail.setDateOfExpense(timestamp.toLocalDateTime().toLocalDate());

        return transactionDetail;
    }
}
