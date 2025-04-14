package com.HexBankAssignHMB.entity;

import com.HexBankAssignHMB.exception.InvalidTransactionException;

public class ZeroBalanceAccount extends Account {
    public ZeroBalanceAccount(long accNumber, Customer customer, float accBalance) {
        super(accNumber,"ZeroBalance", accBalance, customer);
    }

    public boolean withdraw(float amount){
        try{
            if (amount <= 0){
                throw new InvalidTransactionException("Withdrawal amount must be positive.");
            }
            if (accBalance < amount){
                throw new InvalidTransactionException("Insufficient Balance !!! Withdrawal Denied.");
            }
            accBalance -= amount;
            System.out.println("Withdrawal successful! New balance : " + accBalance);
            return true;
        } catch (InvalidTransactionException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

}
