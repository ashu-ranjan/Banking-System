// TASK 11.3

package com.HexBankAssign.bean;

public class ZeroBalanceAccount extends Account {
    public ZeroBalanceAccount(Customer customer) {
        super("ZeroBalance", 0, customer);
    }

    @Override
    public String withdraw(double amount, boolean isTransfer) {
        if (accBalance >= amount){
            accBalance -= amount;
            return "Successfully Withdrawn : " + amount;
        }
        return "Insufficient funds.";
    }

    @Override
    public void calculateInterest() {
//        System.out.println("Zero Balance Accounts can't earn interest.");
    }
}
