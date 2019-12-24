package com.example.Online.Banking.Models.Data;

import com.example.Online.Banking.Models.Account;

import java.util.ArrayList;

public class AccountData {

    static ArrayList<Account> accounts = new ArrayList<>();

    public static ArrayList<Account> getAll(){
        return accounts;
    }

    public static void add(Account account){
        accounts.add(account);
    }

    public static void remove(Account account){
        accounts.remove(account);
    }

    public static Account getById(int num){

        Account theAccount = null;

        for (Account account:accounts) {
            if(account.getId() == num){
                theAccount = account;
            }
        }
        return theAccount;
    }

    public static Boolean moreThanFour(){
        if (accounts.size() < 4){
            return false;
        }
        return true;
    }

    public static Account getByName(String name){
        Account theAccount = null;
        for (Account account: accounts) {
            if(account.getName().equalsIgnoreCase(name)){
                theAccount = account;
            }
        }
        return theAccount;
    }

    public static Boolean negCheck(double num){
        if(num < 0){
            return true;
        }
        return false;
    }
}
