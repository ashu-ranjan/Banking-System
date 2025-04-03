package com.HexBankAssign.bean;

import com.HexBankAssign.service.ICustomerServiceProvider;

import java.util.HashMap;
import java.util.Map;

public class CustomerServiceProviderImpl implements ICustomerServiceProvider {

    protected Map<Long, Account> accounts = new HashMap<>();

    @Override
    public double getAccBalance(long accNumber) {
        Account account = accounts.get(accNumber);
        if (account == null){
            System.out.println("Error: Account Number "+ accNumber + " not found.");
            return -1;
        }
        return account.getAccBalance();
    }

    @Override
    public String deposit(long accNumber, double amount) {
        String depositMessage = accounts.get(accNumber).deposit(amount,false);
        if (!depositMessage.isEmpty()){
            System.out.println(depositMessage);
        }
        return depositMessage;
    }

    @Override
    public double withdraw(long accNumber, double amount) {
        String withdrawMessage = accounts.get(accNumber).withdraw(amount,false);
        if (!withdrawMessage.isEmpty()){
            System.out.println(withdrawMessage);
        }
        if (withdrawMessage.startsWith("Successfully Withdrawn")){
            accounts.get(accNumber).getAccBalance();
        }
        return -1;
    }

    @Override
    public boolean transfer(long fromAcc, long toAcc, double amount) {
        if(accounts.containsKey(fromAcc) && accounts.containsKey(toAcc)){
            String withdrawMessage = accounts.get(fromAcc).withdraw(amount,true);

            if (withdrawMessage.startsWith("Successfully Withdrawn")) {
                accounts.get(toAcc).deposit(amount,true);
                System.out.println("Transferred amount " + amount + " Successfully");
                return true;
            } else{
                System.out.println("Transferred failed : " + withdrawMessage);
            }

        } else {
            System.out.println("Transfer failed due to insufficient balance funds.");
        }
        return false;
    }

    @Override
    public Account getAccDetails(long accNumber) {
        Account acc = accounts.get(accNumber);
        if (acc == null) {
            System.out.println("Error: Account Number " + accNumber + " not found.");
            return null;
        }
        acc.displayAccInfo();
        return acc;
    }


}
