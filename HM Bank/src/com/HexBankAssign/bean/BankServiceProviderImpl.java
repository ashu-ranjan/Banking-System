// TASK 11.7

package com.HexBankAssign.bean;

import com.HexBankAssign.exception.InvalidAccountException;
import com.HexBankAssign.service.IBankServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class BankServiceProviderImpl extends CustomerServiceProviderImpl implements IBankServiceProvider {

    private List<Account> accountList = new ArrayList<>();
    private String branchName;
    private String branchAddress;

    public BankServiceProviderImpl(String branchName, String branchAddress) {
        this.branchName = branchName;
        this.branchAddress = branchAddress;
    }

    @Override
    public Account createAcc(Customer customer, String accType, double balance) {
        Account newAccount;
        if ("Savings".equalsIgnoreCase(accType)) {
            newAccount = new SavingAccount(balance, customer);
        } else if ("Current".equalsIgnoreCase(accType)) {
            newAccount = new CurrentAccount(balance, customer,5000);
        } else if ("ZeroBalance".equalsIgnoreCase(accType)) {
            newAccount = new ZeroBalanceAccount(customer);
        } else {
            System.out.println("Invalid account type.");
            return null;
        }
        accounts.put(newAccount.getAccNumber(), newAccount);
        System.out.println("Account created successfully! Account Number: " + newAccount.getAccNumber());
        return newAccount;
    }

    @Override
    public void listAccount() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("\n--- List of Accounts ---");
        for (Account acc : accounts.values()) {
            acc.displayAccInfo();
        }
    }

    @Override
    public void calculateInterest(long accNumber) throws InvalidAccountException {
        Account acc = accounts.get(accNumber);
        if (acc == null) {
            throw new InvalidAccountException("Error: Account Number " + accNumber + " not found.");
        }

        if (acc instanceof SavingAccount) {
            ((SavingAccount) acc).calculateInterest();
            double interest = acc.getAccBalance() - (acc.getAccBalance() / (1 + 0.04));
            acc.deposit(interest, true);
            System.out.println("Interest of " + interest + " added to Account " + acc.getAccNumber());
        } else {
            System.out.println("Interest calculation is only applicable to Savings Accounts.");
        }
    }


}
