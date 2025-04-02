// TASK 9.2 (CurrentAccount.java)

package com.HexBankAssign.entity;

public class CurrentAccount extends BankAccount{

    // setting overdraft limit
    private static final double OVERDRAFT_LIMIT = 5000.00;

    public CurrentAccount() {} // default constructor

    // Parametrized Constructor
    public CurrentAccount(long accNumber, String customerName, double balance) {
        super(accNumber, customerName, balance);
    }


    // overriding methods of super class
    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Successfully Deposited : " + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (balance + OVERDRAFT_LIMIT >= amount) {
            balance -= amount;
            System.out.println("Successfully Withdrawn : " + amount);
        }
        else
            System.out.println("Overdraft Limit exceeded!");
    }

    @Override
    public void calcInterest() {
        System.out.println("Interest can't be earn from current account.");
    }
}
