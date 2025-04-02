package com.example.register.models;

import java.util.List;

public class changePref {

    String username;
    List<String> checkPrefList;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getCheckPrefList() {
        return checkPrefList;
    }

    public void setCheckPrefList(List<String> checkPrefList) {
        this.checkPrefList = checkPrefList;
    }
}
