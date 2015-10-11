package ru.skuptsov.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.skuptsov.dto.Currency;
import ru.skuptsov.service.api.CentralBankService;
import ru.skuptsov.service.api.CurrencyService;
import ru.skuptsov.service.api.CurrencyServiceURIConstants;
import ru.skuptsov.service.exception.CentralBankException;
import ru.skuptsov.service.exception.CurrencyRateException;
import ru.skuptsov.service.exception.DateParseException;
import ru.skuptsov.service.exception.InvalidCurrencyCodeException;
import ru.skuptsov.service.utils.Utils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

/**
 * Реализация сервиса
 * Created by Сергей on 11.10.2015.
 */
@RestController
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    @Autowired
    CentralBankService centralBankServiceProxy;

    @RequestMapping(value = {CurrencyServiceURIConstants.GET_CURRENCY_RATE_WITH_DATE,
            CurrencyServiceURIConstants.GET_CURRENCY_RATE_WITHOUT_DATE}, method = RequestMethod.GET)
    public @ResponseBody Currency getCurrencyRate(@PathVariable Map<String, String> pathVariables) throws DateParseException, CurrencyRateException,
            CentralBankException, InvalidCurrencyCodeException {

        String date = "";
        Calendar calendar;
        if (pathVariables.containsKey("date")) {
            date = pathVariables.get("date");
            calendar = Utils.getCalendarFromString(date);
        }
        else
        {
            //Если не задан - следующий день
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            date = Utils.getStringFromCalendar(calendar);
        }

        String code = pathVariables.get("code");

        logger.debug("Entering method getCurrencyRate with params : code: " + code + " date : " + date);

        BigDecimal rate = centralBankServiceProxy.getCurrencyRate(code, calendar);

        logger.debug("Leaving method getCurrencyRate");

        return new Currency(code, rate, date);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(CurrencyRateException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleCurrencyRateException(Exception e) {
        return Utils.INTERNAL_ERROR_MESSAGE;
    }

    @ExceptionHandler(CentralBankException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleCentralBankException(Exception e) {
        return "Ошибка сервиса ЦБ : "+e.getMessage();
    }

}
