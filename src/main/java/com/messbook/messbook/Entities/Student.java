package com.messbook.messbook.Entities;

public class Student {
    private String name;
    private String phone_number;
    private String department;
    private String password;
    private String gender;
    private int remaider;
    private String student_image_url;

    public Student() {}

    public Student(String name, String phone_number, String department, String password, String gender, int remaider, String student_image_url) {
        this.name = name;
        this.phone_number = phone_number;
        this.department = department;
        this.password = password;
        this.gender = gender;
        this.remaider = remaider;
        this.student_image_url = student_image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getRemaider() {
        return remaider;
    }

    public void setRemaider(int remaider) {
        this.remaider = remaider;
    }

    public String getStudent_image_url() {
        return student_image_url;
    }

    public void setStudent_image_url(String student_image_url) {
        this.student_image_url = student_image_url;
    }
}