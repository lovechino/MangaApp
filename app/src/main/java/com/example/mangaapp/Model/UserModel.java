package com.example.mangaapp.Model;

import android.text.TextUtils;

public class UserModel {
    private String username,password;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isValidUser(){
        return !TextUtils.isEmpty(username);
    }
    public boolean isValidPassWord(){
        return !TextUtils.isEmpty(password) && password.length() <6;
    }
}
