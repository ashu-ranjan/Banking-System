// TASK 13.1

package com.HexBankAssign.List.bean;

import com.HexBankAssign.List.exception.InvalidAccountException;
import com.HexBankAssign.List.service.IBankServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class BankServiceProviderImpl extends CustomerServiceProviderImpl implements IBankServiceProvider {

    // List used as per the question no.13 of the assignment
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
        accountList.add(newAccount);
        System.out.println("\nAccount created successfully! Your Account Number is : " + newAccount.getAccNumber());
        return newAccount;
    }

    @Override
    public void listAccount() {
        if (accountList.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("\n--- List of Accounts ---");
        for (Account acc : accountList) {
            acc.displayAccInfo();
        }
    }

    @Override
    public void calculateInterest(long accNumber) throws InvalidAccountException {
        Account acc = null;
        for (Account a : accountList) {
            if (a.getAccNumber() == accNumber){
                acc = a;
                break;
            }
        }

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


    @Override
    public Account findAccount(long accNumber) throws InvalidAccountException {
        for (Account a : accountList) {
            if (a.getAccNumber() == accNumber) {
                return a;
            }
        }
        throw new InvalidAccountException("Account Number " + accNumber + " not found.");
    }

}
