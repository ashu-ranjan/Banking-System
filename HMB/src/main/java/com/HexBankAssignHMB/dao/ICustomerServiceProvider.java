package com.HexBankAssignHMB.dao;

import com.HexBankAssignHMB.entity.Account;
import com.HexBankAssignHMB.exception.InvalidAccountException;

public interface ICustomerServiceProvider {
    float getAccBalance(long accNumber);
    float deposit(long accNumber, float amount);
    float withdraw(long accNumber, float amount);
    boolean transfer(long fromAcc, long toAcc, float amount) throws InvalidAccountException;
    Account getAccDetails(long accNumber);
    float calculateInterest(long accNumber, int years);
}
