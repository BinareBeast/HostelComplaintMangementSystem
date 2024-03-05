package com.example.demo;

public class student {
    String name;
    String mail;
    String rollno;
    String ph;
    String hostel;

    public student(String name, String mail, String rollno, String ph, String hostel) {
        this.name = name;
        this.mail = mail;
        this.rollno = rollno;
        this.ph = ph;
        this.hostel = hostel;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getRollno() {
        return rollno;
    }

    public String getPh() {
        return ph;
    }

    public String getHostel() {
        return hostel;
    }
}
