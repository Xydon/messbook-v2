package com.messbook.messbook.Entities;

import java.util.Date;

public class Common_Student_Notification {
    private String semester_id;
    private String title;
    private java.util.Date date;
    private String text;

    public Common_Student_Notification() {
    }

    public Common_Student_Notification(String semester_id, String title, Date date, String text) {
        this.semester_id = semester_id;
        this.title = title;
        this.date = date;
        this.text = text;
    }

    public String getSemester_id() {
        return semester_id;
    }

    public void setSemester_id(String semester_id) {
        this.semester_id = semester_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
