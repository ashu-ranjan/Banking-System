// TASK 13.2

package com.HexBankAssign.Set.service;

import com.HexBankAssign.Set.bean.Account;
import com.HexBankAssign.Set.bean.Customer;
import com.HexBankAssign.Set.exception.InvalidAccountException;

public interface IBankServiceProvider {
    Account createAcc(Customer customer, String accType, double balance) throws InvalidAccountException;
    void listAccount();
    void calculateInterest(long accNumber) throws InvalidAccountException;
}
