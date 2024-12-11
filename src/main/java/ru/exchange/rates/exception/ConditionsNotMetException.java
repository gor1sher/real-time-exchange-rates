package ru.exchange.rates.exception;

public class ConditionsNotMetException extends RuntimeException {

    public ConditionsNotMetException(String str) {
        super(str);
    }
}