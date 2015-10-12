package ru.skuptsov.service.api;

import ru.skuptsov.service.exception.CentralBankException;
import ru.skuptsov.service.exception.CurrencyRateException;
import ru.skuptsov.service.exception.DateParseException;
import ru.skuptsov.service.exception.InvalidCurrencyCodeException;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by Сергей on 11.10.2015.
 */
public interface CentralBankService {

    /**
     * Метод получает данные о значении валюьы на заданную дату
     * @param code - код валюты
     * @param date - дата, на которую необходимо поучить значение валюты
     * @return Значение валюты
     * @throws DateParseException
     * @throws CurrencyRateException
     * @throws CentralBankException
     * @throws InvalidCurrencyCodeException
     */
    BigDecimal getCurrencyRate(String code, Calendar date) throws DateParseException, CurrencyRateException, CentralBankException, InvalidCurrencyCodeException;
}
