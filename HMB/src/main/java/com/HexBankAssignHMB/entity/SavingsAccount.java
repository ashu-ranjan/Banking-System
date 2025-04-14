package com.HexBankAssignHMB.entity;

import com.HexBankAssignHMB.exception.InvalidTransactionException;
import com.HexBankAssignHMB.util.HMBConstants;


public class SavingsAccount extends Account {

    private float interestRate;

    public SavingsAccount(long accNumber,  float accBalance, Customer customer) {
        super(accNumber,"Savings",accBalance, customer);
        this.interestRate = HMBConstants.INTEREST_RATE;
    }

    @Override
    public boolean withdraw(float amount) {
        try {
            if (amount <= 0){
                throw new InvalidTransactionException("Withdrawal amount must be positive.");
            }
            if (accBalance - amount < HMBConstants.MIN_BALANCE){
                throw new InvalidTransactionException("Insufficient Balance !!! Minimum balance of Rs.500 is required.");
            }
            accBalance -= amount;
            System.out.println("Withdrawal successfull! New balance : " + accBalance);
            return true;
        }catch (InvalidTransactionException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public double calculateInterest(int years) {
        return getAccBalance() * interestRate * years;
    }

    public void addInterest(int years){
        double interest = calculateInterest(years);
        accBalance += interest;
        System.out.println("Interest added successfully! New balance : " + accBalance);
    }

}


