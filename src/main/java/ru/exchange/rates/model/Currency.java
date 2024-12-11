package ru.exchange.rates.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Currency {
    private String name;
    private Double priceByUSD;
    private LocalDate localDate;

    public Currency() {
    }
}
