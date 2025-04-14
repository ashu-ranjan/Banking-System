package com.HexBankAssignHMB.dao;

import com.HexBankAssignHMB.entity.*;
import com.HexBankAssignHMB.util.DbConnectionUtil;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class BankServiceProviderImpl extends CustomerServiceProviderImpl implements IBankServiceProvider {

    private static BankServiceProviderImpl bankService;
    private List<Account> accountList;
    private List<Transaction> transactionList;
    private String branchName;
    private String branchAddress;

    public BankServiceProviderImpl(IBankRepository bankRepository, String branchName, String branchAddress) {
        super(bankRepository);
        this.accountList = bankRepository.listAccount();
        this.branchName = branchName;
        this.branchAddress = branchAddress;
    }
    public static BankServiceProviderImpl getInstance() {
        if (bankService == null) {
            Connection connection = DbConnectionUtil.getDbConnection();
            bankService = new BankServiceProviderImpl(new BankRepositoryImpl(connection), "HM Bank (Main Branch)", "123 Annasalai Street, Chennai");
        }
        return bankService;
    }


    @Override
    public Account createAcc(Customer customer, String accType, float balance) {
        return super.bankRepository.createAcc(customer, accType, balance);
    }

    @Override
    public List<Account> listAccount() {
        return super.bankRepository.listAccount();
    }


    @Override
    public List<Transaction> getTransactions(long accNumber, LocalDateTime fromDate, LocalDateTime toDate) {
        return bankRepository.getTransactions(accNumber, fromDate, toDate);
    }



}
