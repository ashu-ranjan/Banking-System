package com.HexBankAssignHMB.entity;

import com.HexBankAssignHMB.exception.InsufficientFundException;
import com.HexBankAssignHMB.exception.OverDraftLimitExcededException;
import com.HexBankAssignHMB.util.HMBConstants;

public class CurrentAccount extends Account {
    private final float overDraftLimit;

    public CurrentAccount(long accNumber, Customer customer, float accBalance) {
        super(accNumber,"Current", accBalance, customer);
        this.overDraftLimit = HMBConstants.OVERDRAFT_LIMIT;
    }


    @Override
    public boolean withdraw(float amount) {
        try {
            if (amount <= 0){
                throw new InsufficientFundException("Withdrawal amount must be positive.");
            }
            if (accBalance - amount < -overDraftLimit){
                throw new OverDraftLimitExcededException("Overdraft limit exceeded for account " + accNumber);
            }
            accBalance -= amount;
            System.out.println("Withdrawal successful! New balance : " + accBalance);
            return true;
        } catch (InsufficientFundException | OverDraftLimitExcededException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

}
