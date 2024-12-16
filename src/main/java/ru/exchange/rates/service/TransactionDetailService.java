package ru.exchange.rates.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.exchange.rates.dal.repository.CurrencyRepository;
import ru.exchange.rates.dal.repository.ExpenseRepository;
import ru.exchange.rates.model.Currency;
import ru.exchange.rates.model.TransactionDetail;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionDetailService {

    private final ExpenseRepository expenseRepository;
    private final CurrencyRepository currencyRepository;

    public List<TransactionDetail> findExpenses() {
        return expenseRepository.getExpenses();
    }

    public Double clientExpensesInCurrency(Long userId, String currency) {
        Currency rate = currencyRepository.getCurrency(currency);
        return sumExpenses(userId) / rate.getPriceInUSD();
    }

    public void addExpense(TransactionDetail transactionDetail, Long userId) {
        expenseRepository.save(checkLimit(transactionDetail, userId));
    }

    public List<TransactionDetail> findByIdExpenses(Long id) {
        return expenseRepository.findById(id);
    }

    private @NotNull Double sumExpenses(Long userId) {
        return expenseRepository.findById(userId)
                .stream()
                .map(TransactionDetail::getExpense)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private TransactionDetail checkLimit(TransactionDetail transactionDetail, Long id) {
        Double limit = findByIdExpenses(id).stream().map(TransactionDetail::getExpense)
                .mapToDouble(Double::doubleValue).sum();

        transactionDetail.setLimitExceeded(transactionDetail.getLimit() < limit);
        return transactionDetail;
    }
}
