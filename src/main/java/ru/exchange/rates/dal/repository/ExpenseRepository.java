package ru.exchange.rates.dal.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.exchange.rates.dal.mappers.ExpenseMappers;
import ru.exchange.rates.model.ExpenseModel;

import java.util.List;

@Repository
@AllArgsConstructor
public class ExpenseRepository {

    private final JdbcTemplate jdbc;
    private ExpenseMappers expenseMappers;

    private static final String FIND_ALL = "SELECT * FROM expenses";
    private static final String FIND_BY_ID = "SELECT * FROM expenses WHERE client_id = ?";
    private static final String INSERT = "INSERT INTO expenses (expense, expense_limit, limit_exceeded, localDate) " +
            "VALUES (?, ?, ?, ?)";

    public List<ExpenseModel> getExpenses() {
        return jdbc.query(FIND_ALL, expenseMappers);
    }

    public List<ExpenseModel> findById(Long id) {
        return (List<ExpenseModel>) jdbc.queryForObject(FIND_BY_ID, expenseMappers, id);
    }

    public void save(ExpenseModel expenseModel) {
        jdbc.update(INSERT,expenseModel.getUserId(), expenseModel.getExpense(), expenseModel.getLimit(),
                expenseModel.getLimitExceeded(), expenseModel.getLocalDateTime());
    }
}
