package com.example.demo;

public class staffdata {
    String name;
    String phno;
    String mail;
    String hoste;
    String desigination;


    public staffdata(String name, String phno, String mail, String hoste,String desigination) {
        this.name = name;
        this.phno = phno;
        this.mail = mail;
        this.hoste = hoste;
        this.desigination = desigination;
    }

    public String getName() {
        return name;
    }

    public String getPhno() {
        return phno;
    }

    public String getMail() {
        return mail;
    }

    public String getHoste() {
        return hoste;
    }
    public String getDesigination() {
        return desigination;
    }
}
