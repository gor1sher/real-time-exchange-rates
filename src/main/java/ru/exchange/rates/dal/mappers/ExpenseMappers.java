package ru.exchange.rates.dal.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.exchange.rates.model.ExpenseModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class ExpenseMappers implements RowMapper<ExpenseModel> {

    @Override
    public ExpenseModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExpenseModel expenseModel = new ExpenseModel();

        expenseModel.setUserId(rs.getLong("client_id"));
        expenseModel.setExpense(rs.getDouble("expense"));
        expenseModel.setLimit(rs.getDouble("expense_limit"));
        expenseModel.setLimitExceeded(rs.getBoolean("limit_exceeded"));
        Timestamp timestamp = rs.getTimestamp("localDate");
        expenseModel.setLocalDateTime(timestamp.toLocalDateTime());

        return expenseModel;
    }
}
