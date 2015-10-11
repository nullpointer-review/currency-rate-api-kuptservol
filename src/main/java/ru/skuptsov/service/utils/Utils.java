package ru.skuptsov.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.skuptsov.service.exception.CurrencyRateException;
import ru.skuptsov.service.exception.DateParseException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Утилитный класс
 * Created by Сергей on 11.10.2015.
 */
public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    private static final String datePattern = "yyyy-MM-dd";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
    public static final String INTERNAL_ERROR_MESSAGE = "Внутрення ошибка приложения";


    public static Calendar getCalendarFromString(String date) throws DateParseException {

        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            logger.error(e.getLocalizedMessage());
            throw new DateParseException(datePattern);
        }

        return cal;
    }

    public static XMLGregorianCalendar getXMLGregorianCalendarFromCalendar(Calendar calendar)
            throws CurrencyRateException {
        Date cDate = calendar.getTime();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(cDate);
        XMLGregorianCalendar xMLGregorianCalendar = null;
        try {
            xMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            logger.error(e.getLocalizedMessage());
            throw new CurrencyRateException("Ошибка конфигурации ");
        }

        return xMLGregorianCalendar;
    }

    public static String getStringFromCalendar(Calendar calendar) throws DateParseException{
        if (calendar != null) {
            return sdf.format(calendar.getTime());
        }

        else throw new DateParseException(datePattern);
    }
}
