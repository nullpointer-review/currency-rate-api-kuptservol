package ru.skuptsov.service.exception;

/**
 * Общий класс ошибки обработки
 * Created by Сергей on 11.10.2015.
 */
public class CurrencyRateException extends Exception {

    public CurrencyRateException(String message) {
        super(message);
    }
}
