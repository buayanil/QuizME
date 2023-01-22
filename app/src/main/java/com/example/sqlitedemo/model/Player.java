package com.example.sqlitedemo.model;

public class Player implements PlayerInt{
    private String username;
    private String password;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Override
    public String getUserName() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
