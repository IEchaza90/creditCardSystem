package com.xCorporation.creditCardSystem.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static LocalDate parseFromString(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        formatter = formatter.withLocale(Locale.getDefault());
        return LocalDate.parse(input, formatter);
    }

    public static String parseToString(LocalDate input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        formatter = formatter.withLocale(Locale.getDefault());
        return formatter.format(input);
    }
}
