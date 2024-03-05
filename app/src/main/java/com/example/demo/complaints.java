package com.example.demo;

public class complaints {
    String rollNo, roomNo, hostel, description, name;

    public complaints() {
    }

    public complaints(String roomNo, String rollNo, String Hostel, String description, String name) {
        this.rollNo = rollNo;
        this.roomNo = roomNo;
        this.hostel = Hostel;
        this.description = description;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getHostel() {
        return hostel;
    }

    public String getDescription() {
        return description;
    }
}
