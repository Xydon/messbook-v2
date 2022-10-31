package com.messbook.messbook.Entities;

import java.sql.Date;

public class StudentInvoice {
    private String student_roll_number;
    private String semester_id;
    private Date month_of_issue;
    private int base_amount;
    private int extra_amount;

    public StudentInvoice() {}

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

    public Date getMonth_of_issue() {
        return month_of_issue;
    }

    public void setMonth_of_issue(Date month_of_issue) {
        this.month_of_issue = month_of_issue;
    }

    public int getBase_amount() {
        return base_amount;
    }

    public void setBase_amount(int base_amount) {
        this.base_amount = base_amount;
    }

    public int getExtra_amount() {
        return extra_amount;
    }

    public void setExtra_amount(int extra_amount) {
        this.extra_amount = extra_amount;
    }

    public StudentInvoice(String student_roll_number, String semester_id, Date month_of_issue, int base_amount, int extra_amount) {
        this.student_roll_number = student_roll_number;
        this.semester_id = semester_id;
        this.month_of_issue = month_of_issue;
        this.base_amount = base_amount;
        this.extra_amount = extra_amount;
    }
}
