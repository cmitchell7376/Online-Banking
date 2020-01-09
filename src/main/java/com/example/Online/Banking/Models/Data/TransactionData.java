package com.example.Online.Banking.Models.Data;

import com.example.Online.Banking.Models.Account;
import com.example.Online.Banking.Models.Transaction;

import java.time.LocalDate;

public class TransactionData {

    public static void makeDepositTransaction(Account account, double amount){
        Transaction transaction = new Transaction("Deposit",account.getBalance(),amount);
        transaction.setTime(LocalDate.now());
        transaction.setAccount(account);
        account.addTransaction(transaction);
    }

    public static void makeWithdrawTransaction(Account account, double amount){
        Transaction transaction = new Transaction("Withdraw",account.getBalance(),amount);
        transaction.setTime(LocalDate.now());
        transaction.setAccount(account);
        account.addTransaction(transaction);
    }

    public static void makeTransferTransaction(Account account, Account account2, Double amount){
        Transaction transaction = new Transaction("Transfer",account.getBalance(),amount);
        transaction.setTime(LocalDate.now());
        transaction.setAccount(account);
        account.addTransaction(transaction);

        Transaction transaction2 = new Transaction("Transfer",account2.getBalance(),amount);
        transaction2.setTime(LocalDate.now());
        transaction2.setAccount(account2);
        account2.addTransaction(transaction2);

    }
}
