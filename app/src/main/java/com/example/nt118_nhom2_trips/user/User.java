package com.example.nt118_nhom2_trips.user;

public class User {
    private  String email;
    private  String Password;
    private  String Fullname, gender, phone;

    public User(String email, String password, String fullname, String gender, String phone) {
        this.email = email;
        Password = password;
        Fullname = fullname;
        this.gender = gender;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return Password;
    }

    public String getFullname() {
        return Fullname;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

}
