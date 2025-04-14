// TASK 13.2

package com.HexBankAssign.Set.bean;

import com.HexBankAssign.Set.exception.DuplicateAccountException;
import com.HexBankAssign.Set.exception.InvalidAccountException;
import com.HexBankAssign.Set.service.IBankServiceProvider;
import com.HexBankAssign.Set.util.AccountComparator;

import java.util.Set;
import java.util.TreeSet;

public class BankServiceProviderImpl extends CustomerServiceProviderImpl implements IBankServiceProvider {

    // Set used as per the question no.13 of the assignment
    private Set<Account> accountSet;

    private String branchName;
    private String branchAddress;

    public BankServiceProviderImpl(String branchName, String branchAddress) {
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.accountSet = new TreeSet<>(new AccountComparator());
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

        for (Account acc : accountSet) {
            if (acc.getCustomer().getFirstName().equalsIgnoreCase(customer.getFirstName()) &&
                    acc.getCustomer().getLastName().equalsIgnoreCase(customer.getLastName())) {
                throw new DuplicateAccountException("Error: Account for " + customer.getFirstName() + " " + customer.getLastName() + " already exists!");
            }
        }

        accountSet.add(newAccount);
        System.out.println("\nAccount created successfully! Your Account Number is : " + newAccount.getAccNumber());
        return newAccount;
    }

    @Override
    public void listAccount() {
        if (accountSet.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("\n--- List of Accounts ---");
        for (Account acc : accountSet) {
            acc.displayAccInfo();
        }
    }

    @Override
    public void calculateInterest(long accNumber) throws InvalidAccountException {
        Account acc = null;
        for (Account a : accountSet) {
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
            double interest = acc.getAccBalance() * 0.04;
            acc.deposit(interest, true);
            System.out.println("Interest of " + interest + " added to Account " + acc.getAccNumber());
        } else {
            System.out.println("Interest calculation is only applicable to Savings Accounts.");
        }
    }

    @Override
    public Account findAccount(long accNumber) throws InvalidAccountException {
        for (Account a : accountSet) {
            if (a.getAccNumber() == accNumber) {
                return a;
            }
        }
        throw new InvalidAccountException("Account Number " + accNumber + " not found.");
    }
}
