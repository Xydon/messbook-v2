package com.messbook.messbook.Entities;

import java.sql.Date;

public class Feedback {
    private String student_roll_number;
    private String semester_id;
    private String mess_id;
    private Date month_of_comment;
    private String text;
    private int rating;

    public Feedback() {
    }

    public String getStudent_roll_number() {
        return student_roll_number;
    }

    public void setStudent_roll_number(String student_roll_number) {
        this.student_roll_number = student_roll_number;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Feedback(String student_roll_number, String semester_id, String mess_id, Date month_of_comment, String text, int rating) {
        this.student_roll_number = student_roll_number;
        this.semester_id = semester_id;
        this.mess_id = mess_id;
        this.month_of_comment = month_of_comment;
        this.text = text;
        this.rating = rating;
    }
}