package com.example.Online.Banking.Models;

import java.util.List;

public class User {

    private int id = 0;
    private String username = "";
    private String password = "";
    private String address = "";
    private List<Account> accounts;

    public User(String username, String password, String address){
        this();
        this.username = username;
        this.password = password;
        this.address = address;
    }

    public User(){
        id += 1;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
