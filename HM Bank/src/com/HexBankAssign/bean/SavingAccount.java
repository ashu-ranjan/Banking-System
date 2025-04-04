// TASK 11.3

package com.HexBankAssign.bean;

public class SavingAccount extends Account {

    private static final double INTEREST_RATE = 0.04; // 4%

    public SavingAccount(double accBalance, Customer customer) {
        super("Savings", accBalance, customer);
        if (accBalance < 500)
            throw new IllegalArgumentException("Minimum balance for savings account is 500.");
    }

    @Override
    public String withdraw(double amount, boolean isTransfer) {
        if (accBalance - amount >= 500){
            accBalance -= amount;
            return "Successfully Withdrawn : " + amount;
        }
        return "Insufficient funds. Minimum balance of 500 required.";
    }

    @Override
    public void calculateInterest() {
        double interest = accBalance * INTEREST_RATE;
        accBalance += interest;
        //System.out.println("Interest of Rs." + interest + " added to your account.");
    }



}
