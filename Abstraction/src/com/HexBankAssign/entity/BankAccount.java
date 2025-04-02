// TASK 9.1 (BankAccount.java)

package com.HexBankAssign.entity;

public abstract class BankAccount {

    // Attributes
    protected long accNumber;
    protected String custName;
    protected double balance;

    public BankAccount(){} // default constructor

    // Parametrized Constructor
    public BankAccount(long accNumber, String custName, double balance) {
        this.accNumber = accNumber;
        this.custName = custName;
        this.balance = balance;
    }

    // Getters
    public long getAccNumber() {
        return accNumber;
    }

    public String getCustName() {
        return custName;
    }

    public double getBalance() {
        return balance;
    }

    // Abstract Methods

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
    public abstract void calcInterest();


    // Display Method
    public void displayInfo(){
        System.out.println("\n----> Account Details <----");
        System.out.printf("\n%-20s : %d%n", "Account Number", getAccNumber());
        System.out.printf("%-20s : %s%n", "Customer Name", getCustName());
        System.out.printf("%-20s : %.2f%n", "Available Balance", getBalance());


    }
}
