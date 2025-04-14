// TASK 13.3

package com.HexBankAssign.Map.service;

import com.HexBankAssign.Map.bean.Account;
import com.HexBankAssign.Map.bean.Customer;
import com.HexBankAssign.Map.exception.InvalidAccountException;

public interface IBankServiceProvider {
    Account createAcc(Customer customer, String accType, double balance) throws InvalidAccountException;
    void listAccount();
    void calculateInterest(long accNumber) throws InvalidAccountException;
}
