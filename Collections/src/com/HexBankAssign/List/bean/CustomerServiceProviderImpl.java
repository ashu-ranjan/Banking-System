// TASK 13.1

package com.HexBankAssign.List.bean;

import com.HexBankAssign.List.exception.InsufficientFundException;
import com.HexBankAssign.List.exception.InvalidAccountException;
import com.HexBankAssign.List.exception.OverDraftLimitExcededException;
import com.HexBankAssign.List.service.ICustomerServiceProvider;


import java.util.ArrayList;
import java.util.List;


public class CustomerServiceProviderImpl implements ICustomerServiceProvider {

    // List used as per the question no. 13 of the assignment
    protected List<Account> accounts = new ArrayList<>();

    @Override
    public double getAccBalance(long accNumber) throws InvalidAccountException {

        Account account = findAccount(accNumber);
        return account.getAccBalance();

    }

    @Override
    public void deposit(long accNumber, double amount) throws InvalidAccountException {
        if (amount < 500) {
            throw new IllegalArgumentException("Deposit amount must be 500 or above.");
        }
        Account acc = findAccount(accNumber);
        acc.deposit(amount, false);
        System.out.println("Deposited " + amount + " to Account " + acc.getAccNumber() + ". New Balance: " + acc.getAccBalance());
    }


    @Override
    public void withdraw(long accNumber, double amount) throws InvalidAccountException, InsufficientFundException, OverDraftLimitExcededException {

        Account acc = findAccount(accNumber);
        acc.withdraw(amount, false);
        System.out.println("Withdrawal successful! New balance: " + acc.getAccBalance());
    }


    @Override
    public void transfer(long fromAcc, long toAcc, double amount) throws InvalidAccountException, InsufficientFundException, OverDraftLimitExcededException {

        Account fromAccount = findAccount(fromAcc);
        Account toAccount = findAccount(toAcc);

        fromAccount.withdraw(amount, true);
        toAccount.deposit(amount, true);

        System.out.println("Transferred amount " + amount + " Successfully from Account " + fromAcc + " to Account " + toAcc);
    }

    @Override
    public Account getAccDetails(long accNumber) throws InvalidAccountException {

        Account acc = findAccount(accNumber);
        acc.displayAccInfo();
        return acc;

    }

    @Override
    public Account findAccount(long accNumber) throws InvalidAccountException {
        return null;
    }

}
