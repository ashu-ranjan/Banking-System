
// TASK 8.2 (SavingAccount Class)

package com.HexBankAssign.entity;

public class SavingAccount extends Account {

    private static final double INTEREST_RATE = 6.5;

    public SavingAccount(long accNumber, double accBalance) {
        super(accNumber, "Saving", accBalance);
    }

    public SavingAccount() {}

    @Override
    public void calcInterest(){
        double interest = (getAccBalance() * INTEREST_RATE) / 100;
        setAccBalance(getAccBalance() + interest);
        System.out.println("Interest of ₹" + interest +" added to your account.");
    }
}
