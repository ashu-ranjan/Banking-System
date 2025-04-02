package com.HexBankAssign.entity;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<Long, Account> accounts = new HashMap<>();

    public Account createAccount (Customer customer, String accType, double balance){
        Account newAccount = new Account(accType, balance, customer);
        accounts.put(newAccount.getAccNumber(), newAccount);
        return newAccount;
    }

    public double getAccountBalance(long accNumber){
        return accounts.get(accNumber).getAccBalance();
    }

    public double deposit(long accNumber, double amount){
        accounts.get(accNumber).deposit(amount);
        return accounts.get(accNumber).getAccBalance();
    }

    public double withdraw(long accNumber, double amount){
        if (accounts.get(accNumber).withdraw(amount)) {
            return accounts.get(accNumber).getAccBalance();
        }
        return -1;

    }

    public boolean transfer(long fromAcc, long toAcc, double amount){
        if(accounts.containsKey(fromAcc) && accounts.containsKey(toAcc)){
            if (accounts.get(fromAcc).withdraw(amount)){
                accounts.get(toAcc).deposit(amount);
                return true;
            }
        }
        return false;
    }

    public void getAccountDetails(long accNumber){
        accounts.get(accNumber).displayAccInfo();
    }
}
