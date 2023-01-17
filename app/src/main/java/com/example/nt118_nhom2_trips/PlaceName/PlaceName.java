package com.example.nt118_nhom2_trips.PlaceName;

public class PlaceName {
    private int id;
    private String title;

    public PlaceName(int id, String title){
        this.id = id;
        this.title = title;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
