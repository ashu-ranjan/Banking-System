// TASK 11

package com.HexBankAssign.service;

import com.HexBankAssign.bean.Account;
import com.HexBankAssign.bean.Customer;
import com.HexBankAssign.exception.InvalidAccountException;

public interface IBankServiceProvider {
    Account createAcc(Customer customer, String accType, double balance);
    void listAccount();
    void calculateInterest(long accNumber) throws InvalidAccountException;
}
