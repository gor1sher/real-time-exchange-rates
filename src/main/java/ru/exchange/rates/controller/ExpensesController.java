package ru.exchange.rates.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.exchange.rates.model.ExpenseModel;
import ru.exchange.rates.service.ExpensesService;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@AllArgsConstructor
public class ExpensesController {

    @NonNull
    private ExpensesService expensesService;

    @GetMapping
    public List<ExpenseModel> getExpenses() {
        return expensesService.getExpenses();
    }

    @GetMapping("/{userId}/{currency}")
    public Double getExpensesByCurrency(@PathVariable(name = "userId") Long userId,
                                        @PathVariable(name = "currency") String currency) {

        return expensesService.getExpensesByCurrency(userId, currency);
    }

    @PostMapping("/{userId}")
    public void addExpense(@PathVariable(name = "userId") Long userId,
                           @RequestBody ExpenseModel expenseModel) {

        ExpenseModel checkExpense = checkLimit(expenseModel, userId);
        expensesService.addExpense(checkExpense);
    }

    @PostMapping("/{userId}/limit")
    public void setLimit(@PathVariable(name = "userId") Long userId,
                         @RequestBody Integer limit) {

        expensesService.addLimit(userId, limit);
    }

    private ExpenseModel checkLimit(ExpenseModel expenseModel, Long id) {
        Double limit = expensesService.findByIdExpenses(id).stream().map(ExpenseModel::getExpense)
                .mapToDouble(Double::doubleValue).sum();

        if (expenseModel.getLimit() < limit) {
            expenseModel.setLimitExceeded(true);
            return expenseModel;
        }
        expenseModel.setLimitExceeded(false);
        return expenseModel;
    }
}
