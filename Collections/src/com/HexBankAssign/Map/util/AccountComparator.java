// TASK 13.3

package com.HexBankAssign.Map.util;

import com.HexBankAssign.Map.bean.Account;

import java.util.Comparator;

public class AccountComparator implements Comparator<Account> {
    @Override
    public int compare(Account a1, Account a2) {
        String FullName1 = a1.getCustomer().getFirstName() + " " + a1.getCustomer().getLastName();
        String FullName2 = a2.getCustomer().getFirstName() + " " + a2.getCustomer().getLastName();

        return FullName1.compareToIgnoreCase(FullName2);
    }
}
