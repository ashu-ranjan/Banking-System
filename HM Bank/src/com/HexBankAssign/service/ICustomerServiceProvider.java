// TASK 11.4

package com.HexBankAssign.service;
import com.HexBankAssign.bean.Account;
import com.HexBankAssign.exception.InsufficientFundException;
import com.HexBankAssign.exception.InvalidAccountException;
import com.HexBankAssign.exception.OverDraftLimitExcededException;

public interface ICustomerServiceProvider {
    double getAccBalance(long accNumber) throws InvalidAccountException;
    void deposit(long accNumber, double amount) throws InvalidAccountException;
    void withdraw(long accNumber, double amount) throws InvalidAccountException, InsufficientFundException, OverDraftLimitExcededException;
    void transfer(long fromAcc, long toAcc, double amount) throws InvalidAccountException, InsufficientFundException, OverDraftLimitExcededException;
    Account getAccDetails(long accNumber) throws InvalidAccountException;
}
