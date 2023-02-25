package com.example.nt118_nhom2_trips.Tour;

public class Tour {
    private String id_tour;
    private String image_tour;
    private String name;
    private String day;
    private String address;
    private String email;
    private String phone;
    private String price;
    private String detail;
    private String title;

    public Tour(String id_tour, String image_tour, String name, String day, String address, String email, String phone, String price, String detail, String title) {
        this.id_tour = id_tour;
        this.image_tour = image_tour;
        this.name = name;
        this.day = day;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.price = price;
        this.detail = detail;
        this.title = title;
    }

    public Tour() {
    }

    public String getId_tour() {
        return id_tour;
    }

    public void setId_tour(String id_tour) {
        this.id_tour = id_tour;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_tour() {
        return image_tour;
    }

    public void setImage_tour(String image_tour) {
        this.image_tour = image_tour;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
