package com.example.Online.Banking.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String username = "";

    @NotNull
    @Size(min = 5, max = 20, message = "must be between 5 - 20 characters")
    private String password = "";

    private String address = "";

    @ManyToMany
    private List<Account> accounts;

    public User(){

    }

    public User(String username, String password, String address){
        this.username = username;
        this.password = password;
        this.address = address;
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
