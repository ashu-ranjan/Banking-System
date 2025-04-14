// TASK 13.3

package com.HexBankAssign.Map.service;
import com.HexBankAssign.Map.bean.Account;
import com.HexBankAssign.Map.exception.InsufficientFundException;
import com.HexBankAssign.Map.exception.InvalidAccountException;
import com.HexBankAssign.Map.exception.OverDraftLimitExcededException;

public interface ICustomerServiceProvider {
    double getAccBalance(long accNumber) throws InvalidAccountException;
    void deposit(long accNumber, double amount) throws InvalidAccountException;
    void withdraw(long accNumber, double amount) throws InvalidAccountException, InsufficientFundException, OverDraftLimitExcededException;
    void transfer(long fromAcc, long toAcc, double amount) throws InvalidAccountException, InsufficientFundException, OverDraftLimitExcededException;
    Account getAccDetails(long accNumber) throws InvalidAccountException;
    Account findAccount(long accNumber) throws InvalidAccountException;
}
