// TASK 13.3

package com.HexBankAssign.Map.bean;

import com.HexBankAssign.Map.exception.InsufficientFundException;
import com.HexBankAssign.Map.exception.InvalidAccountException;
import com.HexBankAssign.Map.exception.OverDraftLimitExcededException;
import com.HexBankAssign.Map.service.ICustomerServiceProvider;

import java.util.HashMap;
import java.util.Map;


public abstract class CustomerServiceProviderImpl implements ICustomerServiceProvider {

    // Map used as per the question no. 13 of the assignment
    protected Map<Long, Account> accounts;

    public CustomerServiceProviderImpl(){
        this.accounts = new HashMap<>();
    }

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

    public abstract Account createAcc(Customer customer, long accNumber, String accType, double balance);

    @Override
    public Account findAccount(long accNumber) throws InvalidAccountException {
        return null;
    }
}
