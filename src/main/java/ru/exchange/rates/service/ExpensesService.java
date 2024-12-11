package ru.exchange.rates.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.exchange.rates.dal.repository.CurrencyRepository;
import ru.exchange.rates.dal.repository.ExpenseRepository;
import ru.exchange.rates.model.Currency;
import ru.exchange.rates.model.ExpenseModel;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpensesService {

    @NonNull
    private ExpenseRepository expenseRepository;

    @NonNull
    private CurrencyRepository currencyRepository;

    public List<ExpenseModel> getExpenses() {
        return expenseRepository.getExpenses();
    }

    public Double getExpensesByCurrency(Long userId, String currency){
        Currency rate = currencyRepository.getCurrency(currency);
        Double sum = expenseRepository.findById(userId).stream().map(ExpenseModel::getExpense)
                .mapToDouble(Double::doubleValue).sum();

        return sum/rate.getPriceByUSD();
    }

    public void addExpense(ExpenseModel expenseModel) {
        expenseRepository.save(expenseModel);
    }

    public List<ExpenseModel> findByIdExpenses(Long id) {
        return expenseRepository.findById(id);
    }
}
