package ru.skuptsov.service.exception;

import java.text.ParseException;

/**
 * Ошибка парсинга даты
 * Created by Сергей on 11.10.2015.
 */
public class DateParseException extends ParseException {

    private static final String message="Invalid date pattern must be %s";
    /**
     * Приемлемый формат даты
     */
    private String datePattern;

    public DateParseException(String s, int errorOffset) {
        super(s, errorOffset);
    }

    public DateParseException(String datePattern) {
        super(String.format(message,datePattern), 0);
        this.datePattern = datePattern;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }
}
