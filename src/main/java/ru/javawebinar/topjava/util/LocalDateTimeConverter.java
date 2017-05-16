package ru.javawebinar.topjava.util;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

/**
 * Created by admin on 16.05.2017.
 */

public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String s) {
        if (s.isEmpty()) return null;
        return LocalDateTime.parse(s, ISO_LOCAL_DATE_TIME);
    }
}
