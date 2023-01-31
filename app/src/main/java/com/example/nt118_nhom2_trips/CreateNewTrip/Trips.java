package com.example.nt118_nhom2_trips.CreateNewTrip;

public class Trips {
    private String trip_id;
    private String id;
    private String start_date_trip;
    private String end_date_trip;
    private String name_trip;
    private String place_trip;



    public Trips(String trip_id,String id, String start_date_trip, String end_date_trip, String name_trip, String place_trip) {
        this.trip_id = trip_id;
        this.id = id;
        this.start_date_trip = start_date_trip;
        this.end_date_trip = end_date_trip;
        this.name_trip = name_trip;
        this.place_trip = place_trip;
    }

    public  Trips () {
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

    public String getStart_date_trip() {
        return start_date_trip;
    }

    public void setStart_date_trip(String start_date_trip) {
        this.start_date_trip = start_date_trip;
    }

    public String getEnd_date_trip() {
        return end_date_trip;
    }

    public void setEnd_date_trip(String end_date_trip) {
        this.end_date_trip = end_date_trip;
    }

    public String getName_trip() {
        return name_trip;
    }

    public void setName_trip(String name_trip) {
        this.name_trip = name_trip;
    }

    public String getPlace_trip() {
        return place_trip;
    }

    public void setPlace_trip(String place_trip) {
        this.place_trip = place_trip;
    }
}
