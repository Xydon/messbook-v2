package com.messbook.messbook.Entities;

public class Mess_Change_Application {
    private String student_roll;
    private String semester_id;
    private String reason;
    private int status;

    public Mess_Change_Application() {
    }

    public Mess_Change_Application(String student_roll, String semester_id, String reason, int status) {
        this.student_roll = student_roll;
        this.semester_id = semester_id;
        this.reason = reason;
        this.status = status;
    }

    public String getStudent_roll() {
        return student_roll;
    }

    public void setStudent_roll(String student_roll) {
        this.student_roll = student_roll;
    }

    public String getSemester_id() {
        return semester_id;
    }

    public void setSemester_id(String semester_id) {
        this.semester_id = semester_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
