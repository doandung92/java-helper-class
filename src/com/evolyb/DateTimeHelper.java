package com.evolyb;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeHelper {
    public static String getTimeInStringFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static boolean checkDateIsSundayOrSaturday(String date, String format)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(date));
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static BigDecimal getDaysBetween(String dateFrom, String dateTo, String format)
            throws ParseException {
        if (StringHelper.isNullOrEmpty(dateFrom) || StringHelper.isNullOrEmpty(dateTo))
            return new BigDecimal(0);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        long miniSeconds = sdf.parse(dateTo).getTime() - sdf.parse(dateFrom).getTime();
        return new BigDecimal(miniSeconds/(24*60*60*1000));
    }

    public static Date addDate(Date date, int numberOfDays) {
        date.setTime(date.getTime() + numberOfDays * 24*60*60*1000);
        return date;
    }

}
