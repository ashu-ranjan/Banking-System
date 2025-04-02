// TASK 10

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

    public String deposit(long accNumber, double amount){
        String depositMessage = accounts.get(accNumber).deposit(amount,false);
        if (!depositMessage.isEmpty()){
            System.out.println(depositMessage);
        }
        return depositMessage;
    }

    public double withdraw(long accNumber, double amount){
        String withdrawMessage = accounts.get(accNumber).withdraw(amount,false);
        if (!withdrawMessage.isEmpty()){
            System.out.println(withdrawMessage);
        }
        if (withdrawMessage.startsWith("Withdrawal Successful")) {
            return accounts.get(accNumber).getAccBalance();
        }
        return -1;

    }

    public boolean transfer(long fromAcc, long toAcc, double amount){
        if(accounts.containsKey(fromAcc) && accounts.containsKey(toAcc)){
                String withdrawMessage = accounts.get(fromAcc).withdraw(amount,true);

                if (withdrawMessage.startsWith("Withdrawal Successful")) {
                    accounts.get(toAcc).deposit(amount,true);
                    System.out.println("Transferred amount " + amount + " Successfully");
                    return true;
                } else{
                    System.out.println("Transferred failed : " + withdrawMessage);
                }

            } else {
                System.out.println("Transfer failed due to insufficient balance funds.");
            }
        return false;
    }

    public void getAccountDetails(long accNumber){
        accounts.get(accNumber).displayAccInfo();
    }
}
