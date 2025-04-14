// TASK 13.2

package com.HexBankAssign.Set.bean;

import com.HexBankAssign.Set.exception.InsufficientFundException;
import com.HexBankAssign.Set.exception.InvalidAccountException;
import com.HexBankAssign.Set.exception.OverDraftLimitExcededException;
import com.HexBankAssign.Set.service.ICustomerServiceProvider;
import com.HexBankAssign.Set.util.AccountComparator;

import java.util.Set;
import java.util.TreeSet;


public class CustomerServiceProviderImpl implements ICustomerServiceProvider {

    // Set used as per the question no. 13 of the assignment
    protected Set<Account> accounts;

    public CustomerServiceProviderImpl(){
        this.accounts = new TreeSet<>(new AccountComparator());
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

    @Override
    public Account findAccount(long accNumber) throws InvalidAccountException {
        return null;
    }
}
