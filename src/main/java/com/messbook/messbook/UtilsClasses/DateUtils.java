package com.messbook.messbook.UtilsClasses;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static java.sql.Date convertDateToSqlFormat(Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static Date convertToUtilFormat(java.sql.Date date) {
        return new Date(date.getTime());
    }

    public static java.sql.Date getLastDateOfMonth(java.sql.Date firstDayOfMonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDayOfMonth);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        Date lastDayOfMonth = calendar.getTime();

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return java.sql.Date.valueOf(sdf.format(lastDayOfMonth));
    }
}
