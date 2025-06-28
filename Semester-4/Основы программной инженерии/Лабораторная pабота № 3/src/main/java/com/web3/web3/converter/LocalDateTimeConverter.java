package com.web3.web3.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.DateTimeConverter;
import jakarta.faces.convert.FacesConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


/**
 * Конвертер для работы с датой и временем в формате LocalDateTime.
 * Используется в JSF для корректного отображения и обработки дат.
 */
@FacesConverter("localDateTimeConverter")
public class LocalDateTimeConverter extends DateTimeConverter {


    /**
     * Создаёт новый экземпляр конвертера LocalDateTime.
     * Настраивает формат отображения даты и времени по умолчанию.
     */

    public LocalDateTimeConverter() {
        setLocale(Locale.US); // Set the desired locale
        setPattern("dd/MM/yyyy HH:mm:ss");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            // Parse the input string to LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            return LocalDateTime.parse(value, formatter);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof LocalDateTime) {
            // Format LocalDateTime as a string using the specified pattern
            LocalDateTime dateTime = (LocalDateTime) value;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getPattern());
            return dateTime.format(formatter);
        }
        return null;
    }
}
