package com.messbook.messbook.Entities;

public class Mess {
    private String name;
    private String hostel_name;
    private String phone_number;
    private String cmail_id;
    private String password;
    private int due;
    private String mess_image_url;

    public Mess() {
    }

    public Mess(String name, String hostel_name, String phone_number, String cmail_id, String password, int due, String mess_image_url) {
        this.name = name;
        this.hostel_name = hostel_name;
        this.phone_number = phone_number;
        this.cmail_id = cmail_id;
        this.password = password;
        this.due = due;
        this.mess_image_url = mess_image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostel_name() {
        return hostel_name;
    }

    public void setHostel_name(String hostel_name) {
        this.hostel_name = hostel_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCmail_id() {
        return cmail_id;
    }

    public void setCmail_id(String cmail_id) {
        this.cmail_id = cmail_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDue() {
        return due;
    }

    public void setDue(int due) {
        this.due = due;
    }

    public String getMess_image_url() {
        return mess_image_url;
    }

    public void setMess_image_url(String mess_image_url) {
        this.mess_image_url = mess_image_url;
    }
}