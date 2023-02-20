package com.example.nt118_nhom2_trips.user;

public class User {

    private    String email;
    private    String Password;
    private    String gender, phone, birthday;

    public User() {
    }

    public User(String birthday, String gender, String phone) {
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return Password;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

}
