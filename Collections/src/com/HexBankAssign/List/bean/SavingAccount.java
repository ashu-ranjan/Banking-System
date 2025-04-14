// TASK 13.1

package com.HexBankAssign.List.bean;

import com.HexBankAssign.List.exception.InsufficientFundException;

public class SavingAccount extends Account {

    private static final double INTEREST_RATE = 0.04; // 4%

    public SavingAccount(double accBalance, Customer customer) {
        super("Savings", accBalance, customer);
        if (accBalance < 500)
            throw new IllegalArgumentException("Minimum balance for savings account must be 500.");
    }

    @Override
    public String withdraw(double amount, boolean isTransfer) throws InsufficientFundException {
        if (accBalance - amount >= 500){
            accBalance -= amount;
            return "Successfully Withdrawn : " + amount;
        }
        throw new InsufficientFundException("Insufficient funds! Available Balance : " + accBalance);
    }

    @Override
    public void calculateInterest() {
        double interest = accBalance * INTEREST_RATE;
        accBalance += interest;
        //System.out.println("Interest of Rs." + interest + " added to your account.");
    }



}
