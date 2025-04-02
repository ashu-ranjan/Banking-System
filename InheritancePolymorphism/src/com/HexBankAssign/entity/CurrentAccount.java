
// TASK 8.2 (CurrentAccount class)

package com.HexBankAssign.entity;

public class CurrentAccount extends Account{

    private static final double OVERDRAFT_LIMIT = 5000.00;


    public CurrentAccount(long accNumber, double accBalance) {
        super(accNumber, "Current", accBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");

        } else if (amount > getAccBalance() + OVERDRAFT_LIMIT) {
            System.out.println("Withdrawal exceeds overdraft limit!");

        } else {
            accBalance = getAccBalance() - amount;
            System.out.println("Successfully Withdrawn: ₹" + amount);
        }
    }

    @Override
    public void calcInterest() {
        System.out.println("Interest is not applicable for current accounts.");
    }
}
