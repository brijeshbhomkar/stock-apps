package com.nse.services.participant.util;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat INPUT_PATTERN = new SimpleDateFormat("dd-MMM-yyyy");
    private static final SimpleDateFormat OUTPUT_PATTERN = new SimpleDateFormat("dd-mmm-yyyy");

    @SneakyThrows
    public static Date toDate(final String date) {
        return new Date(date);
    }

    public static String getDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        return strDate.replaceAll("/", "");
    }

    public static String getLastThrusdayOfMonth() {
        LocalDate lastThrusdayOfMonth = LocalDate.now().with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyy");
        return formatter.format(lastThrusdayOfMonth);
    }

    public static String formateDate() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyy");
        return formatter.format(localDate);
    }

    public static String getThrusdayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        Date thrusdayOfWeek = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyy");
        return format.format(thrusdayOfWeek);
    }

    public static String formatExpiryDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyy");
        return format.format(date);
    }
}
