package com.atta.weswap;

public class User {

    private int id;
    private String username, email, password, phone;


    public User(int id, String username, String email, String password, String phone, String birthday, String location, String job) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
