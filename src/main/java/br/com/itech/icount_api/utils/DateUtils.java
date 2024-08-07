package br.com.itech.icount_api.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateUtils {

    public static String parseDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dtFormatada;
        dtFormatada = date.format(formatter);
        return dtFormatada;
    }
}
