package com.example.demo;

public class User {
    public String getName() {
        return name;
    }

    public User(String name, String mail, String roll, String ph, String hos) {
        this.name = name;
        this.mail = mail;
        this.roll = roll;
        this.ph = ph;
        this.hos = hos;
    }

    public String getMail() {
        return mail;
    }

    public String getRoll() {
        return roll;
    }

    public String getPh() {
        return ph;
    }

    public String getHos() {
        return hos;
    }

    public  String name,mail,roll,ph,hos;

    public User() {
    }
}
