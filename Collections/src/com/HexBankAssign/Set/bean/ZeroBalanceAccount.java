// TASK 13.2

package com.HexBankAssign.Set.bean;

import com.HexBankAssign.Set.exception.InsufficientFundException;

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
