package com.example.nt118_nhom2_trips.CreateNewTrip;

public class Activity {
    private String activity_id;
    private String trip_id;
    private String id;
    private String day;
    private String time;
    private String name;
    private String detail;

    public Activity() {
    }

    public Activity(String activity_id, String trip_id, String id, String day, String time, String name, String detail) {
        this.activity_id = activity_id;
        this.trip_id = trip_id;
        this.id = id;
        this.day = day;
        this.time = time;
        this.name = name;
        this.detail = detail;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
