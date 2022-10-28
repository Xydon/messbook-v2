package com.messbook.messbook.Entities;

import java.util.Date;

public class Semester_Details {
    private String id;
    private java.util.Date start_date;
    private java.util.Date end_date;
    private int price_per_meal;
    private int mess_advance_price;

    public Semester_Details() {
    }


    public Semester_Details(String id, Date start_date, Date end_date, int price_per_meal, int mess_advance_price) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.price_per_meal = price_per_meal;
        this.mess_advance_price = mess_advance_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getPrice_per_meal() {
        return price_per_meal;
    }

    public void setPrice_per_meal(int price_per_meal) {
        this.price_per_meal = price_per_meal;
    }

    public int getMess_advance_price() {
        return mess_advance_price;
    }

    public void setMess_advance_price(int mess_advance_price) {
        this.mess_advance_price = mess_advance_price;
    }
}