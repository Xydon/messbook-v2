package com.messbook.messbook.UtilsClasses;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

public class DateUtils {
    private static final LinkedList<String> MonthList = new LinkedList<String>();

    public static String getMonthAt(int monthIndex) {
        if(MonthList.size() == 0) {
            MonthList.add("January");
            MonthList.add("February");
            MonthList.add("March");
            MonthList.add("April");
            MonthList.add("May");
            MonthList.add("June");
            MonthList.add("July");
            MonthList.add("August");
            MonthList.add("September");
            MonthList.add("October");
            MonthList.add("November");
            MonthList.add("December");
        }
        return MonthList.get(monthIndex);
    }
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
