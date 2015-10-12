package ru.skuptsov.service.api;

import ru.skuptsov.dto.Currency;
import ru.skuptsov.service.exception.CentralBankException;
import ru.skuptsov.service.exception.CurrencyRateException;
import ru.skuptsov.service.exception.DateParseException;
import ru.skuptsov.service.exception.InvalidCurrencyCodeException;

import java.util.Map;

/**
 * Сервис валют
 * Created by Сергей on 11.10.2015.
 */
public interface CurrencyService {

    /**
     * Метод получает данные о значении валюьы на заданную дату
     * @param pathVariables
     * @return Объект валюта
     * @throws DateParseException
     * @throws CurrencyRateException
     * @throws CentralBankException
     * @throws InvalidCurrencyCodeException
     */
    Currency getCurrencyRate(Map<String, String> pathVariables) throws DateParseException, CurrencyRateException, CentralBankException, InvalidCurrencyCodeException;
}
