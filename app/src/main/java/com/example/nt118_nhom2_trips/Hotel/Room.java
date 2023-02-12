package com.example.nt118_nhom2_trips.Hotel;

public class Room {
    private String id_hotel;
    private String id_room;
    private String img_room;
    private String room_name;
    private int price;
    private int size;
    private int sale;
    private int guest;
    private boolean bathroom;
    private boolean breakfast;
    private boolean wifi;

    public Room() {
    }

    public Room(String id_hotel, String id_room, String img_room, String room_name, int price, int size, int sale, int guest, boolean bathroom, boolean breakfast, boolean wifi) {
        this.id_hotel = id_hotel;
        this.id_room = id_room;
        this.img_room = img_room;
        this.room_name = room_name;
        this.price = price;
        this.size = size;
        this.sale = sale;
        this.guest = guest;
        this.bathroom = bathroom;
        this.breakfast = breakfast;
        this.wifi = wifi;
    }

    public String getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(String id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getId_room() {
        return id_room;
    }

    public void setId_room(String id_room) {
        this.id_room = id_room;
    }

    public String getImg_room() {
        return img_room;
    }

    public void setImg_room(String img_room) {
        this.img_room = img_room;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getGuest() {
        return guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }

    public boolean isBathroom() {
        return bathroom;
    }

    public void setBathroom(boolean bathroom) {
        this.bathroom = bathroom;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }
}
