package com.messbook.messbook.Entities;

public class Department {
    private String hod;
    private String location;
    private String phone;

    public Department() {
    }

    public Department(String hod, String location, String phone) {
        this.hod = hod;
        this.location = location;
        this.phone = phone;
    }

    public String getHod() {
        return hod;
    }

    public void setHod(String hod) {
        this.hod = hod;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}