// TASK 11.3

package com.HexBankAssign.bean;

public class CurrentAccount extends Account {

    private double OVERDRAFT_LIMIT;

    public CurrentAccount(double accBalance, Customer customer, double OVERDRAFT_LIMIT) {
        super("Current", accBalance, customer);
        this.OVERDRAFT_LIMIT = OVERDRAFT_LIMIT;
    }

    @Override
    public String withdraw(double amount, boolean isTransfer) {
        if (accBalance + OVERDRAFT_LIMIT >= amount){
            accBalance -= amount;
            return "Successfully Withdrawn : " + amount;
        }
        return "Overdraft Limit Exceeded!";
    }

    @Override
    public void calculateInterest() {
//        System.out.println("Interest not provided on Current Accounts.");
    }


}
