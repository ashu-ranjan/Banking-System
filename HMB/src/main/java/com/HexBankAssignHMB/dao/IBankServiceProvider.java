package com.HexBankAssignHMB.dao;

import com.HexBankAssignHMB.entity.Account;
import com.HexBankAssignHMB.entity.Customer;
import com.HexBankAssignHMB.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface IBankServiceProvider {
    Account createAcc(Customer customer, String accType, float balance);
    List<Account> listAccount();
    List<Transaction> getTransactions(long accNumber, LocalDateTime fromDate, LocalDateTime toDate);
}

