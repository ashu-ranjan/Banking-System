
// TASK 8.1

package com.HexBankAssign.entity;

public class Account {

    private long accNumber;
    private String accType;
    protected double accBalance; // protected type for subclasses


    public Account(){}

    public Account(long accNumber, String accType, double accBalance) {
        this.accNumber = accNumber;
        this.accType = accType;
        this.accBalance = accBalance;
    }

    public long getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(long accNumber) {
        this.accNumber = accNumber;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public double getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(double accBalance) {
        if (accBalance >= 0) {
            this.accBalance = accBalance;
        }
        else
            System.out.println("Deposit amount must be positive.");
    }

    public void deposit (float amount){
        deposit((double) amount);
    }

    public void deposit (int amount){
        deposit((double) amount);
    }

    public void deposit(double amount){
        if (amount >= 500){
            accBalance += amount;
            System.out.println("Successfully Deposited : ₹" + amount);
        }
        else
            System.out.println("Invalid amount! Deposit amount must be greater than 500.");
    }

    public void withdraw(float amount){
        withdraw((double) amount);
    }

    public void withdraw(int amount){
        withdraw((double) amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        }
        else if (amount > accBalance) {
            System.out.println("Insufficient Balance !!!");
        }
        else {
            accBalance -= amount;
            System.out.println("Successfully Withdrew : ₹" + amount);
        }
    }

    public void accInfo(){
        System.out.println("\n---> Accounts Detail <---");
        System.out.printf("\n%-20s: %d%n", "Account Number", getAccNumber());
        System.out.printf("%-20s: %s%n", "Account Type", getAccType());
        System.out.printf("%-20s: %.2f%n", "Available Balance", getAccBalance());
    }

    public void calcInterest(){
        System.out.println("Not Applicable.");
    }
}
