package com.example.nt118_nhom2_trips.user;

public class User {
    private    String email;
    private    String Password;
    private    String fullname, gender, phone;

    public User() {
    }

    public User(String email, String password, String fullname, String gender, String phone) {
        this.email = email;
        this.Password = password;
        this.fullname = fullname;
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
        return fullname;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

}
