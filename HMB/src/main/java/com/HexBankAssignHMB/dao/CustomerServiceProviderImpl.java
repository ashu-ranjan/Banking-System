package com.HexBankAssignHMB.dao;

import com.HexBankAssignHMB.entity.Account;
import com.HexBankAssignHMB.exception.InvalidAccountException;

public class CustomerServiceProviderImpl implements ICustomerServiceProvider {

    protected IBankRepository bankRepository;

    public CustomerServiceProviderImpl(IBankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public float getAccBalance(long accNumber) {
        float balance = bankRepository.getAccBalance(accNumber);

        if (balance == -1) {
            System.out.println("Failed to get balance for account: " + accNumber);
        }
        return balance;
    }

    @Override
    public float deposit(long accNumber, float amount) {
        float result = bankRepository.deposit(accNumber, amount);

        if (result == -1) {
            System.out.println("Deposit failed for account: " + accNumber);
        }
        return result;
    }

    @Override
    public float withdraw(long accNumber, float amount) {
        //String formattedAccNumber = formatAccountNumber(accNumber);
        float result = bankRepository.withdraw(accNumber, amount);

        if (result == -1) {
            System.out.println("Withdrawal failed for account: " + accNumber);
        }
        return result;
    }

    @Override
    public boolean transfer(long fromAcc, long toAcc, float amount) throws InvalidAccountException {

        boolean success = bankRepository.transfer(fromAcc, toAcc, amount);
        if (!success) {
            throw new InvalidAccountException("Transfer failed between accounts");
        }
        return true;
    }

    @Override
    public Account getAccDetails(long accNumber) {
        return bankRepository.getAccDetails(accNumber);
    }

    @Override
    public float calculateInterest(long accNumber, int years) {
        return bankRepository.calculateInterest(accNumber, years);
    }
}
