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
     *
     * @param code
     * @param date
     * @return
     * @throws DateParseException
     * @throws CurrencyRateException
     * @throws CentralBankException
     * @throws InvalidCurrencyCodeException
     */
    BigDecimal getCurrencyRate(String code, Calendar date) throws DateParseException, CurrencyRateException, CentralBankException, InvalidCurrencyCodeException;
}
