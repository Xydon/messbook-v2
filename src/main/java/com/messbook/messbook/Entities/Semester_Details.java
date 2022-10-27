package com.messbook.messbook.Entities;

import java.util.Date;

public class Semester_Details {
    private java.util.Date start_date;
    private java.util.Date end_date;
    private int price_per_meal;

    public Semester_Details() {
    }

    public Semester_Details(Date start_date, Date end_date, int price_per_meal) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.price_per_meal = price_per_meal;
    }

    public java.util.Date getStart_date() {
        return start_date;
    }

    public void setStart_date(java.util.Date start_date) {
        this.start_date = start_date;
    }

    public java.util.Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(java.util.Date end_date) {
        this.end_date = end_date;
    }

    public int getPrice_per_meal() {
        return price_per_meal;
    }

    public void setPrice_per_meal(int price_per_meal) {
        this.price_per_meal = price_per_meal;
    }
}