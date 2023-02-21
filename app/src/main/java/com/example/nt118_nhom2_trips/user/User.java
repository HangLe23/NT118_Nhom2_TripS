package com.example.nt118_nhom2_trips.user;

public class User {

    private    String email;
    private    String Password;
    private    String gender, phone, birthday, fullname;

    public User() {
    }

    public User(String fullname, String birthday, String gender, String phone) {
        this.fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public String getBirthday() {
        return birthday;
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
