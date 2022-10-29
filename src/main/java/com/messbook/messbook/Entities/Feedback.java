package com.messbook.messbook.Entities;

import java.sql.Date;

public class Feedback {
    private String semester_id;
    private String mess_id;
    private Date month_of_comment;
    private String text;

    public Feedback() {
    }


    public Feedback(String semester_id, String mess_id, Date month_of_comment, String text) {
        this.semester_id = semester_id;
        this.mess_id = mess_id;
        this.month_of_comment = month_of_comment;
        this.text = text;
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

    public Date getMonth_of_comment() {
        return month_of_comment;
    }

    public void setMonth_of_comment(Date month_of_comment) {
        this.month_of_comment = month_of_comment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}