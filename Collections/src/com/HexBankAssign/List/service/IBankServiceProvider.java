// TASK 13.1

package com.HexBankAssign.List.service;

import com.HexBankAssign.List.bean.Account;
import com.HexBankAssign.List.bean.Customer;
import com.HexBankAssign.List.exception.InvalidAccountException;

public interface IBankServiceProvider {
    Account createAcc(Customer customer, String accType, double balance) throws InvalidAccountException;
    void listAccount();
    void calculateInterest(long accNumber) throws InvalidAccountException;
}
