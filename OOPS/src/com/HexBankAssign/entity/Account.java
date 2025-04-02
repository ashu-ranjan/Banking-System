package com.HexBankAssign.entity;

// TASK 7.2

public class Account {

    private long accountNumber;
    private String accountType;
    private double accountBalance;
    private static final double INTEREST_RATE = 4.5;

    public Account(){

    }

    public Account(long accountNumber, String accountType, double accountBalance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void deposit(double amount){
        if (accountBalance > 0) {
            accountBalance += amount;
            System.out.println("Successfully Deposited : ₹" + amount);
        }
        else
            System.out.println("Invalid Deposit! Deposit amount must be grater than 500.");
    }

    public void withdraw(double amount){
        if (amount > 0 && amount <= accountBalance){
            accountBalance -= amount;
            System.out.println("Successfully Withdrew : ₹" + amount);
        }
        else
            System.out.println("Insufficient Balance or Invalid Amount.");
    }

    public void calcInterest(){
        if (accountType.equalsIgnoreCase("Saving")){
            double interest = (accountBalance * INTEREST_RATE) / 100;
            accountBalance += interest;
            System.out.println("Interest of ₹" + interest + " added to your account balance.");
        }
        else
            System.out.println("Interest is only applicable for Saving accounts.");
    }

    public void accountInfo(){


        System.out.println("\n---> Accounts Detail <---");
        System.out.printf("\n%-20s: %d%n", "Account Number", getAccountNumber());
        System.out.printf("%-20s: %s%n", "Account Type", getAccountType());
        System.out.printf("%-20s: %.2f%n", "Available Balance", getAccountBalance());
    }

}
