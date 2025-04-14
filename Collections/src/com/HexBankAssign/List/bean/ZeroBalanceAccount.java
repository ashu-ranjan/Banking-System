// TASK 13.1

package com.HexBankAssign.List.bean;

import com.HexBankAssign.List.exception.InsufficientFundException;

public class ZeroBalanceAccount extends Account {
    public ZeroBalanceAccount(Customer customer) {
        super("ZeroBalance", 0, customer);
    }

    @Override
    public String withdraw(double amount, boolean isTransfer) throws InsufficientFundException {
        if (accBalance >= amount){
            accBalance -= amount;
            return "Successfully Withdrawn : " + amount;
        }
        throw new InsufficientFundException("Insufficient funds! Available Balance : " + accBalance);
    }

    @Override
    public void calculateInterest() {
//        System.out.println("Zero Balance Accounts can't earn interest.");
    }
}
