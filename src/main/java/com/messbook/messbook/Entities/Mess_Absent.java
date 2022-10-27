package com.messbook.messbook.Entities;

import java.util.Date;

public class Mess_Absent{
private String semester_id;
private String mess_id;
private java.util.Date start_date;
private java.util.Date end_date;

    public Mess_Absent() {}
    public Mess_Absent(String semester_id, String mess_id, Date start_date, Date end_date) {
        this.semester_id = semester_id;
        this.mess_id = mess_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getSemester_id(){
        return semester_id;
        }

public void setSemester_id(String semester_id){
        this.semester_id=semester_id;
        }

public String getMess_id(){
        return mess_id;
        }

public void setMess_id(String mess_id){
        this.mess_id=mess_id;
        }

public java.util.Date getStart_date(){
        return start_date;
        }

public void setStart_date(java.util.Date start_date){
        this.start_date=start_date;
        }

public java.util.Date getEnd_date(){
        return end_date;
        }

public void setEnd_date(java.util.Date end_date){
        this.end_date=end_date;
        }
        }