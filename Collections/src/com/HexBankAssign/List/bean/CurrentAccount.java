// TASK 13.1

package com.HexBankAssign.List.bean;

import com.HexBankAssign.List.exception.InsufficientFundException;
import com.HexBankAssign.List.exception.OverDraftLimitExcededException;

public class CurrentAccount extends Account {

    private double OVERDRAFT_LIMIT;

    public CurrentAccount(double accBalance, Customer customer, double OVERDRAFT_LIMIT) {
        super("Current", accBalance, customer);
        this.OVERDRAFT_LIMIT = OVERDRAFT_LIMIT;
    }

    @Override
    public String withdraw(double amount, boolean isTransfer) throws InsufficientFundException, OverDraftLimitExcededException {
        if (amount > (accBalance + OVERDRAFT_LIMIT)) {
            throw new OverDraftLimitExcededException("Error: Overdraft limit exceeded for account " + accNumber);
        }
        accBalance -= amount;
        if (!isTransfer) {
            return "Successfully withdrawn " + amount + ". New Balance: " + accBalance;
        }
        return null;
    }


    @Override
    public void calculateInterest() {
//        System.out.println("Interest not provided on Current Accounts.");
    }


}
