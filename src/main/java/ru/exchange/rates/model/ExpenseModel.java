package ru.exchange.rates.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpenseModel {
    private Long userId;
    private Double expense;
    private Double limit;
    private Boolean limitExceeded;
    private LocalDateTime localDateTime;
}
