package ru.skuptsov.service.exception;

/**
 * ОНеверный код валюты
 * Created by Сергей on 11.10.2015.
 */
public class InvalidCurrencyCodeException extends Exception {

    public InvalidCurrencyCodeException(String message) {
        super(message);
    }
}
