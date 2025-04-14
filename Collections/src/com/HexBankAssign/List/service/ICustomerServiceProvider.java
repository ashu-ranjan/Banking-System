// TASK 13.1

package com.HexBankAssign.List.service;
import com.HexBankAssign.List.bean.Account;
import com.HexBankAssign.List.exception.InsufficientFundException;
import com.HexBankAssign.List.exception.InvalidAccountException;
import com.HexBankAssign.List.exception.OverDraftLimitExcededException;

public interface ICustomerServiceProvider {
    double getAccBalance(long accNumber) throws InvalidAccountException;
    void deposit(long accNumber, double amount) throws InvalidAccountException;
    void withdraw(long accNumber, double amount) throws InvalidAccountException, InsufficientFundException, OverDraftLimitExcededException;
    void transfer(long fromAcc, long toAcc, double amount) throws InvalidAccountException, InsufficientFundException, OverDraftLimitExcededException;
    Account getAccDetails(long accNumber) throws InvalidAccountException;
    Account findAccount(long accNumber) throws InvalidAccountException;
}
