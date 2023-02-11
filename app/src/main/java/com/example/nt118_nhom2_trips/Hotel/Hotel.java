package com.example.nt118_nhom2_trips.Hotel;

public class Hotel {
    private String id_hotel;
    private String address;
    private String detail;
    private String imageUrl;
    private String name;
    private String rate;
    private String sale;

    public Hotel() {
    }

    public Hotel(String id_hotel, String address, String detail, String imageUrl, String name, String rate, String sale) {
        this.id_hotel = id_hotel;
        this.address = address;
        this.detail = detail;
        this.imageUrl = imageUrl;
        this.name = name;
        this.rate = rate;
        this.sale = sale;
    }

    public String getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(String id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getAddress() {
        return this.address;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String isSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}
