package com.messbook.messbook.Entities;
public class Mess_Change_Application {
    private String mess_id;
    private String student_roll_number;
    private String semester_id;
    private String reason;
    private int status;

    public Mess_Change_Application() {
    }

    public Mess_Change_Application(String mess_id, String student_roll_number, String semester_id, String reason, int status) {
        this.mess_id = mess_id;
        this.student_roll_number = student_roll_number;
        this.semester_id = semester_id;
        this.reason = reason;
        this.status = status;
    }

    public String getMess_id() {
        return mess_id;
    }

    public void setMess_id(String mess_id) {
        this.mess_id = mess_id;
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


