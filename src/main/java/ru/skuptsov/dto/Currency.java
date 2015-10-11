package ru.skuptsov.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Валюта
 * Created by Сергей on 11.10.2015.
 */
public class Currency implements Serializable {

    /**
     * Трёхсимвольный код валюты
     */
    private String code;
    /**
     * Курс
     */
    private BigDecimal rate;
    /**
     * Дата актуальности
     */
    private String date;

    public Currency(String code, BigDecimal rate, String date) {
        this.code = code;
        this.rate = rate;
        this.date = date;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
