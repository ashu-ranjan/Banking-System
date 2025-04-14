// TASK 13.3

package com.HexBankAssign.Map.bean;

import com.HexBankAssign.Map.exception.DuplicateAccountException;
import com.HexBankAssign.Map.exception.InvalidAccountException;
import com.HexBankAssign.Map.service.IBankServiceProvider;
import com.HexBankAssign.Map.util.AccountComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankServiceProviderImpl extends CustomerServiceProviderImpl implements IBankServiceProvider {

    // Map used as per the question no.13 of the assignment
    private Map<Long, Account> accountMap;

    private String branchName;
    private String branchAddress;

    public BankServiceProviderImpl(String branchName, String branchAddress) {
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.accountMap = new HashMap<>();
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

        for (Account existingAccount : accountMap.values()) {
            String existingFullName = existingAccount.getCustomer().getFirstName() + " " + existingAccount.getCustomer().getLastName();
            String newFullName = customer.getFirstName() + " " + customer.getLastName();

            if (existingFullName.equalsIgnoreCase(newFullName)) {
                throw new DuplicateAccountException("Error: Account already exists for " + newFullName);
            }
        }

        accountMap.put(newAccount.getAccNumber(), newAccount);
        System.out.println("\nAccount created successfully! Your Account Number is : " + newAccount.getAccNumber());
        return newAccount;
    }

    @Override
    public void listAccount() {
        List<Account> acc = new ArrayList<>(accountMap.values());
        acc.sort(new AccountComparator());

        System.out.println("Account sorted by customer name.");
        for (Account a : acc)
            a.displayAccInfo();
    }

    @Override
    public void calculateInterest(long accNumber) throws InvalidAccountException {
        Account acc = accountMap.get(accNumber);

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
    public Account createAcc(Customer customer, long accNumber, String accType, double balance) {
        return null;
    }

    @Override
    public Account findAccount(long accNumber) throws InvalidAccountException {
        if (!accountMap.containsKey(accNumber)) {
            throw new InvalidAccountException("Account Number " + accNumber + " not found.");
        }
        return accountMap.get(accNumber);

    }
}
