package ru.skuptsov.service.exception;

/**
 * Ошибка ответа от ЦБ
 * Created by Сергей on 11.10.2015.
 */
public class CentralBankException extends Exception {

    public CentralBankException(String message) {
        super(message);
    }
}
