package com.example.Online.Banking.Models;

public class Account {

    private int id = 0;
    private String name = "";
    private double balance = 0.0;
    private User user;

    public Account(String name, double balance){
        this();
        this.name = name;
        this.balance = balance;
    }

    public Account(){
        id += 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public User getUser() {
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void depost(double amount){
        balance += amount;
    }

    public void withdrew(double amount){
        balance -= amount;
    }
}
