package com.example.nt118_nhom2_trips.CreateNewTrip;

public class Date {
    private String day;
    private String date;

    public Date(String day, String date) {
        this.day = day;
        this.date = date;
    }

    public Date() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
