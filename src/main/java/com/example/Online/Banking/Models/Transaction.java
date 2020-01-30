package com.example.Online.Banking.Models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private Object time;

    private Double previousAmount;

    private Double amount;

    @ManyToOne
    private Account account;

    public Transaction(String name, Double previousAmount, Double amount){
        this.name = name;
        this.previousAmount = previousAmount;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Object getTime() {
        return time;
    }

    public Double getPreviousAmount() {
        return previousAmount;
    }

    public Double getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreviousAmount(Double previousAmount) {
        this.previousAmount = previousAmount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setTime(Object time) {
        this.time = time;
    }
}
