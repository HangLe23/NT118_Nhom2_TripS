package com.example.nt118_nhom2_trips.Hotel;

public class Hotel {
    private int id_hotel;
    private String address;
    private String detail;
    private String imageUrl;
    private String name;
    private int rate;
    private boolean sale;

    public Hotel() {
    }

    public Hotel(int id_hotel, String address, String detail, String imageUrl, String name, int rate, boolean sale) {
        this.id_hotel = id_hotel;
        this.address = address;
        this.detail = detail;
        this.imageUrl = imageUrl;
        this.name = name;
        this.rate = rate;
        this.sale = sale;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }
}
