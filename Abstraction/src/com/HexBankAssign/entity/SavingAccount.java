// TASK 9.2 (SavingAccount.java)

package com.HexBankAssign.entity;

public class SavingAccount extends BankAccount{


    // setting interest rate
    private static final double INTEREST_RATE = 0.04; // 4% interest on savings

    // Parametrized Constructor
    public SavingAccount(long accNumber, String customerName, double balance) {
        super(accNumber, customerName, balance);
    }

    public SavingAccount() {} // default constructor

    // Overriding methods of super class

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Successfully Deposited : " + amount);

    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance){
            balance -= amount;
            System.out.println("Successfully Withdrawn : " + amount);
        }
        else
            System.out.println("Insufficient Balance!");
    }

    @Override
    public void calcInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.println("Interest of amount ₹" + interest + " added to your savings account. ");
    }
}
