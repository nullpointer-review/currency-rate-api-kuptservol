package ru.skuptsov.service.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Стартовая точка приложения spring-boot
 * Created by Сергей on 11.10.2015.
 */
@SpringBootApplication
public class CurrencyRateStart {

        public static void main(String[] args) {
            SpringApplication.run(CurrencyRateStart.class, args);
        }
}
