package ru.exchange.rates.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDetail {
    private Long userId;
    private Double expense;
    private Double limit;
    private Boolean limitExceeded;
    private LocalDate dateOfExpense;
}
