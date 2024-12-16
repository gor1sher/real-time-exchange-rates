package ru.exchange.rates.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    private String name;
    private Double priceInUSD;
    private LocalDate dateOfCourseUpdate;
}
