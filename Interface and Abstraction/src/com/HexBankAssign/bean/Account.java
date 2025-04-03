// TASK 11.2

package com.HexBankAssign.bean;

public abstract class Account {

    private static long lastAccNo = 1001;
    protected long accNumber;
    protected String accType;
    protected double accBalance;
    protected Customer customer;

    public Account(String accType, double accBalance, Customer customer) {
        this.accNumber = lastAccNo++;
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

    // deposit method

    public String deposit(double amount, boolean isTransfer){
        if (amount >= 500){
            accBalance += amount;
            return isTransfer ? "" : "Successfully Deposited : " + amount;
        }
        return isTransfer ? "" : "Deposit amount must be 500 or above.";
    }

    // abstract withdraw method defined

    public abstract String withdraw(double amount, boolean isTransfer);
    public abstract void calculateInterest();

    public void displayAccInfo(){
        System.out.println("\n----> Account Details <----");
        System.out.printf("\n%-20s : %d%n", "Account Number", getAccNumber());
        System.out.printf("%-20s : %s%n", "Account Type", getAccType());
        System.out.printf("%-20s : %.2f%n", "Available Balance", getAccBalance());
        customer.displayCustInfo();
    }

}

