package com.messbook.messbook.Entities;

public class Hostel {
    private String warden_name;
    private String warden_phone;
    private String gender;

    public Hostel() {
    }

    public Hostel(String warden_name, String warden_phone, String gender) {
        this.warden_name = warden_name;
        this.warden_phone = warden_phone;
        this.gender = gender;
    }

    public String getWarden_name() {
        return warden_name;
    }

    public void setWarden_name(String warden_name) {
        this.warden_name = warden_name;
    }

    public String getWarden_phone() {
        return warden_phone;
    }

    public void setWarden_phone(String warden_phone) {
        this.warden_phone = warden_phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}