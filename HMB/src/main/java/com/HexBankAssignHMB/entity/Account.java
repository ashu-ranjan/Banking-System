package com.HexBankAssignHMB.entity;

import com.HexBankAssignHMB.exception.InsufficientFundException;
import com.HexBankAssignHMB.exception.InvalidTransactionException;
import com.HexBankAssignHMB.exception.OverDraftLimitExcededException;
import com.HexBankAssignHMB.util.HMBConstants;

public abstract class Account {

    protected long accNumber;
    protected String accType;
    protected float accBalance;
    protected Customer customer;

    public Account(long accNumber, String accType,float accBalance, Customer customer) {
        this.accNumber = accNumber;
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

    public boolean withdraw(float amount) throws OverDraftLimitExcededException, InsufficientFundException{
        try {
            if (amount <= 0){
                throw new InvalidTransactionException("Withdrawal amount must be positive.");
            }
            if (amount < accBalance){
                throw new InsufficientFundException("Insufficient Balance !!!");
            }
            accBalance = accBalance - amount;
            System.out.println("Withdrawal successfull! New balance : " + accBalance);
            return true;
        } catch (InvalidTransactionException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }


    @Override
    public String toString() {
        return String.format(
                        "\n%-20s : %s" +
                        "\n%-20s : %d" +
                        "\n%-20s : %s" +
                        "\n%-20s : ₹%.2f",
                "Account Holder", customer.getFirstName() + " " + customer.getLastName(),
                "Account Number", getAccNumber(),
                "Account Type", getAccType(),
                "Available Balance", getAccBalance());
    }
}
