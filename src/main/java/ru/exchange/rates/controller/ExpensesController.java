package ru.exchange.rates.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.exchange.rates.model.TransactionDetail;
import ru.exchange.rates.service.TransactionDetailService;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@AllArgsConstructor
public class ExpensesController {

    @NonNull
    private TransactionDetailService transactionDetailService;

    @GetMapping
    public List<TransactionDetail> getExpenses() {
        return transactionDetailService.findExpenses();
    }

    @GetMapping("/{userId}/{currency}")
    public Double getExpensesByCurrency(@PathVariable(name = "userId") Long userId,
                                        @PathVariable(name = "currency") String currency) {

        return transactionDetailService.clientExpensesInCurrency(userId, currency);
    }

    @PostMapping("/{userId}")
    public void addExpense(@PathVariable(name = "userId") Long userId,
                           @RequestBody TransactionDetail transactionDetail) {

        transactionDetailService.addExpense(transactionDetail, userId);
    }
}
