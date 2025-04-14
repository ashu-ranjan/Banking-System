// TASK 13.2

package com.HexBankAssign.Set.service;
import com.HexBankAssign.Set.bean.Account;
import com.HexBankAssign.Set.exception.InsufficientFundException;
import com.HexBankAssign.Set.exception.InvalidAccountException;
import com.HexBankAssign.Set.exception.OverDraftLimitExcededException;

public interface ICustomerServiceProvider {
    double getAccBalance(long accNumber) throws InvalidAccountException;
    void deposit(long accNumber, double amount) throws InvalidAccountException;
    void withdraw(long accNumber, double amount) throws InvalidAccountException, InsufficientFundException, OverDraftLimitExcededException;
    void transfer(long fromAcc, long toAcc, double amount) throws InvalidAccountException, InsufficientFundException, OverDraftLimitExcededException;
    Account getAccDetails(long accNumber) throws InvalidAccountException;
    Account findAccount(long accNumber) throws InvalidAccountException;
}
