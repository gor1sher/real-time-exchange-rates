package ru.exchange.rates.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LimitModel {
    private Long userId;
    private Integer limit;
    private LocalDate localDate;
}
