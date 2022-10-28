package com.messbook.messbook.Entities;

public class Mess {
    private String id;
    private String name;
    private String hostel_name;
    private String phone_number;
    private String password;
    private int due;
    private String mess_image_url;

    public Mess() {
    }


    public Mess(String id, String name, String hostel_name, String phone_number, String password, int due, String mess_image_url) {
        this.id = id;
        this.name = name;
        this.hostel_name = hostel_name;
        this.phone_number = phone_number;
        this.password = password;
        this.due = due;
        this.mess_image_url = mess_image_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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