// TASK 11.4

package com.HexBankAssign.service;
import com.HexBankAssign.bean.Account;

public interface ICustomerServiceProvider {
    double getAccBalance(long accNumber);
    String deposit(long accNumber, double amount);
    double withdraw(long accNumber, double amount);
    boolean transfer(long fromAcc, long toAcc, double amount);
    Account getAccDetails(long accNumber);
}
