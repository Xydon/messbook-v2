package com.messbook.messbook.Entities;

public class Semester_Info {
    private String student_roll_number;
    private String hostel_name;
    private String mess_id;
    private int semester_number;
    private int mess_advance_paid;

    public Semester_Info() {
    }

    public Semester_Info(String student_roll_number, String hostel_name, String mess_id, int semester_number, int mess_advance_paid) {
        this.student_roll_number = student_roll_number;
        this.hostel_name = hostel_name;
        this.mess_id = mess_id;
        this.semester_number = semester_number;
        this.mess_advance_paid = mess_advance_paid;
    }

    public String getStudent_roll_number() {
        return student_roll_number;
    }

    public void setStudent_roll_number(String student_roll_number) {
        this.student_roll_number = student_roll_number;
    }

    public String getHostel_name() {
        return hostel_name;
    }

    public void setHostel_name(String hostel_name) {
        this.hostel_name = hostel_name;
    }

    public String getMess_id() {
        return mess_id;
    }

    public void setMess_id(String mess_id) {
        this.mess_id = mess_id;
    }

    public int getSemester_number() {
        return semester_number;
    }

    public void setSemester_number(int semester_number) {
        this.semester_number = semester_number;
    }

    public int getMess_advance_paid() {
        return mess_advance_paid;
    }

    public void setMess_advance_paid(int mess_advance_paid) {
        this.mess_advance_paid = mess_advance_paid;
    }
}