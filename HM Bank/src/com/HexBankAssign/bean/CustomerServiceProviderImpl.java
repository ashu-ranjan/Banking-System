package com.HexBankAssign.bean;

import com.HexBankAssign.exception.InsufficientFundException;
import com.HexBankAssign.exception.InvalidAccountException;
import com.HexBankAssign.exception.OverDraftLimitExcededException;
import com.HexBankAssign.service.ICustomerServiceProvider;

import java.util.HashMap;
import java.util.Map;

public class CustomerServiceProviderImpl implements ICustomerServiceProvider {

    protected Map<Long, Account> accounts = new HashMap<>();

    @Override
    public double getAccBalance(long accNumber) throws InvalidAccountException {
        Account account = accounts.get(accNumber);
        if (account == null){
            throw new InvalidAccountException("Error: Account Number "+ accNumber + " not found.");
        }
        return account.getAccBalance();
    }

    @Override
    public void deposit(long accNumber, double amount) throws InvalidAccountException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }

        Account acc = accounts.get(accNumber);
        if (acc == null) {
            throw new InvalidAccountException("Account Number " + accNumber + " not found.");
        }

        acc.deposit(amount, false);
        System.out.println("Deposited " + amount + " to Account " + acc.getAccNumber() + ". New Balance: " + acc.getAccBalance());
    }


    @Override
    public void withdraw(long accNumber, double amount) throws InvalidAccountException, InsufficientFundException, OverDraftLimitExcededException {
        Account acc = accounts.get(accNumber);

        if (acc == null) {
            throw new InvalidAccountException("Error: Account Number " + accNumber + " not found.");
        }
        acc.withdraw(amount, false);

        System.out.println("Withdrawal successful! New balance: " + acc.getAccBalance());
    }


    @Override
    public void transfer(long fromAcc, long toAcc, double amount) throws InvalidAccountException, InsufficientFundException, OverDraftLimitExcededException {
        Account fromAccount = accounts.get(fromAcc);
        Account toAccount = accounts.get(toAcc);

        if (fromAccount == null) {
            throw new InvalidAccountException("Error: From Account Number " + fromAcc + " not found.");
        }
        if (toAccount == null) {
            throw new InvalidAccountException("Error: To Account Number " + toAcc + " not found.");
        }

        fromAccount.withdraw(amount, true);
        toAccount.deposit(amount, true);
        System.out.println("Transferred amount " + amount + " Successfully from Account " + fromAcc + " to Account " + toAcc);
    }

    @Override
    public Account getAccDetails(long accNumber) throws InvalidAccountException {
        Account acc = accounts.get(accNumber);
        if (acc == null) {
            throw new InvalidAccountException("Error: Account Number " + accNumber + " not found.");
        }
        acc.displayAccInfo();
        return acc;
    }


}
