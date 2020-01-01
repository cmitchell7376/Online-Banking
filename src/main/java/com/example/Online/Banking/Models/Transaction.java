package com.example.Online.Banking.Models;


public class Transaction {

    private int id = 0;
    private String name;
    private Object time;
    private Double previousAmount;
    private Double amount;

    private Account account;

    public Transaction(String name, Double previousAmount, Double amount){
        this();
        this.name = name;
        this.previousAmount = previousAmount;
        this.amount = amount;
    }

    public Transaction(){
        id += 1;
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
