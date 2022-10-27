package com.messbook.messbook.Entities;

import java.util.Date;

public class Mess_Extra_Entry {
    private String semester_id;
    private String mess_id;
    private String item_name;
    private java.util.Date date;

    public Mess_Extra_Entry() {
    }

    public Mess_Extra_Entry(String semester_id, String mess_id, String item_name, Date date) {
        this.semester_id = semester_id;
        this.mess_id = mess_id;
        this.item_name = item_name;
        this.date = date;
    }

    public String getSemester_id() {
        return semester_id;
    }

    public void setSemester_id(String semester_id) {
        this.semester_id = semester_id;
    }

    public String getMess_id() {
        return mess_id;
    }

    public void setMess_id(String mess_id) {
        this.mess_id = mess_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }
}