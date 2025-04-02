// TASK 10.2

package com.HexBankAssign.entity;

public class Account {

    private static long accNumCounter = 1001;
    private long accNumber;
    private String accType;
    private double accBalance;
    private Customer customer;

    public Account(String accType, double accBalance, Customer customer) {
        this.accNumber = accNumCounter++;
        this.accType = accType;
        this.accBalance = accBalance;
        this.customer = customer;
    }

    public long getAccNumber() {
        return accNumber;
    }

    public String getAccType() {
        return accType;
    }

    public double getAccBalance() {
        return accBalance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String deposit(double amount, boolean isTransfer){
        if (amount >= 500){
            accBalance += amount;
            return isTransfer ? "" : "Successfully Deposited : " + amount;
        }
        return isTransfer ? "" : "Deposit amount must be 500 or above.";
    }

    public String withdraw(double amount, boolean isTransfer) {
        if (accBalance >= amount) {
            accBalance -= amount;
            return "Withdrawal Successful: " + amount;
        } else {
            return "Insufficient funds. Withdrawal failed.";
        }
    }


    public void displayAccInfo(){
        System.out.println("\n----> Account Details <----");
        System.out.printf("\n%-20s : %d%n", "Account Number", getAccNumber());
        System.out.printf("%-20s : %s%n", "Account Type", getAccType());
        System.out.printf("%-20s : %.2f%n", "Available Balance", getAccBalance());
        customer.displayCustInfo();
    }

}
