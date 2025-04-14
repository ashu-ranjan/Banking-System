package com.HexBankAssignHMB.dao;

import com.HexBankAssignHMB.entity.Account;
import com.HexBankAssignHMB.entity.Customer;
import com.HexBankAssignHMB.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface IBankRepository {

    Account createAcc(Customer customer, String accType, float accBalance);

    List<Account> listAccount();

    float getAccBalance(long accNumber);

    float deposit(long accNumber, float amount);

    float withdraw(long accNumber, float amount);

    boolean transfer(long fromAccNumber, long toAccNumber, float amount);

    Account getAccDetails(long accNumber);

    float calculateInterest(long accNumber, int years);

    void logTransaction(Transaction transaction);

    List<Transaction> getTransactions(long accNumber, LocalDateTime fromDate, LocalDateTime toDate);
}
