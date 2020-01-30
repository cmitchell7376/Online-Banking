package com.example.Online.Banking.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, max = 25, message = "Field empty")
    private String name = "";

    private double balance = 0.0;

    @ManyToMany(mappedBy = "accounts")
    private List<User> users;

    @OneToMany()
    @JoinColumn(name = "account_id")
    private List<Transaction> transactionList = new ArrayList<Transaction>();

    public Account(){
    }

    public Account(String name, double balance){
        this.name = name;
        this.balance = balance;
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

    public List<User> getUser() {
        return users;
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

    public void setUser(List<User> users) {
        this.users = users;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public void deposit(double amount){
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
