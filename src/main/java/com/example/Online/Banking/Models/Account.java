package com.example.Online.Banking.Models;

import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private int id;
    private static int nextId = 1;
    private List<Transaction> transactionList = new ArrayList<Transaction>();

    @NotNull
    @Size(min = 1, max = 25, message = "Field empty")
    private String name = "";
    private double balance = 0.0;
    private User user;

    public Account(String name, double balance){
        this();
        this.name = name;
        this.balance = balance;
    }

    public Account(){
        id = nextId;
        nextId++;
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

    public List<Transaction> getTransactionList() {
        return transactionList;
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

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public void depost(double amount){
        balance += amount;
    }

    public void withdrew(double amount){
        balance -= amount;
    }

    public void addTransaction(Transaction transaction){
        transactionList.add(transaction);
    }

    public void removeTransaction(Transaction transaction){
        transactionList.remove(transaction);
    }
}
