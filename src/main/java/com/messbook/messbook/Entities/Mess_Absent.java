package com.messbook.messbook.Entities;

import java.sql.Date;

public class Mess_Absent {
    private String student_roll_number;
    private String semester_id;
    private String mess_id;
    private Date start_date;
    private Date end_date;

    public Mess_Absent() {
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

    public Mess_Absent(String student_roll_number, String semester_id, String mess_id, Date start_date, Date end_date) {
        this.student_roll_number = student_roll_number;
        this.semester_id = semester_id;
        this.mess_id = mess_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}