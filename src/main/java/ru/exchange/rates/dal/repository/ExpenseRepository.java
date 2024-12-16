package ru.exchange.rates.dal.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.exchange.rates.dal.mappers.TransactionalDetailMappers;
import ru.exchange.rates.model.TransactionDetail;

import java.util.List;

@Repository
@AllArgsConstructor
public class ExpenseRepository {

    private final JdbcTemplate jdbc;
    private TransactionalDetailMappers transactionalDetailMappers;

    private static final String FIND_ALL = "SELECT * FROM expenses";
    private static final String FIND_BY_ID = "SELECT * FROM expenses WHERE client_id = ?";
    private static final String INSERT = "INSERT INTO expenses (expense, expense_limit, limit_exceeded, localDate) " +
            "VALUES (?, ?, ?, ?)";

    public List<TransactionDetail> getExpenses() {
        return jdbc.query(FIND_ALL, transactionalDetailMappers);
    }

    public List<TransactionDetail> findById(Long id) {
        return (List<TransactionDetail>) jdbc.queryForObject(FIND_BY_ID, transactionalDetailMappers, id);
    }

    public void save(TransactionDetail transactionDetail) {
        jdbc.update(INSERT, transactionDetail.getUserId(), transactionDetail.getExpense(), transactionDetail.getLimit(),
                transactionDetail.getLimitExceeded(), transactionDetail.getDateOfExpense());
    }
}
