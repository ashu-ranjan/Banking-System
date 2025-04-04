
package com.HexBankAssign.app;

import com.HexBankAssign.bean.Account;
import com.HexBankAssign.bean.BankServiceProviderImpl;
import com.HexBankAssign.bean.Customer;
import com.HexBankAssign.exception.InsufficientFundException;
import com.HexBankAssign.exception.InvalidAccountException;
import com.HexBankAssign.exception.OverDraftLimitExcededException;

import java.util.Scanner;

public class HMBank {
    public static void main(String[] args) throws InvalidAccountException, InsufficientFundException, OverDraftLimitExcededException {
        Scanner sc = new Scanner(System.in);

        BankServiceProviderImpl bank = new BankServiceProviderImpl("HM Bank", "Arrah, Bihar");

        while (true){

            System.out.println("\n---> HM Bank Welcomes You!! Create Account to proceed <---");
            System.out.print("\nEnter First Name : ");
            String firstName = sc.nextLine();
            System.out.print("Enter Last Name : ");
            String lastName = sc.nextLine();
            System.out.print("Enter Email : ");
            String email = sc.nextLine();
            System.out.print("Enter Phone Number: ");
            String phone = sc.nextLine();
            System.out.print("Enter Address : ");
            String address = sc.nextLine();
            Customer customer = new Customer(firstName, lastName, email, phone, address);
            System.out.print("Enter Account Type (Savings/Current) : ");
            String accType = sc.nextLine();
            System.out.print("Enter Initial Balance : ");
            double balance = sc.nextDouble();
            Account acc = bank.createAcc(customer, accType, balance);


            while (true) {

                try {
                    System.out.println("\n---> Banking Operation <---");
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Transfer");
                    System.out.println("4. Get Balance");
                    System.out.println("5. Get Account Details");
                    System.out.println("6. Register Another Customer");
                    System.out.println("7. List All Accounts");
                    System.out.println("8. Calculate Interest");
                    System.out.println("9. Exit");

                    System.out.print("\nChoose an option to proceed : ");
                    int choice = sc.nextInt();
                    sc.nextLine();

                    if (choice == 6)
                        break;
                    else if (choice == 9) {
                        System.out.println("Exiting !!! Thank you for using HexBank Services.");
                        System.exit(0);
                    }

                    switch (choice) {
                        case 1:
                            System.out.print("Enter Account Number : ");
                            long accDeposit = sc.nextLong();
                            System.out.print("Enter Amount : ");
                            bank.deposit(accDeposit, sc.nextDouble());
                            break;

                        case 2:
                            System.out.print("Enter Account Number : ");
                            long accWithdraw = sc.nextLong();
                            System.out.print("Enter Amount : ");
                            bank.withdraw(accWithdraw, sc.nextDouble());
                            break;

                        case 3:
                            System.out.print("Enter From Account: ");
                            long fromAcc = sc.nextLong();
                            sc.nextLine();
                            System.out.print("Enter To Account: ");
                            long toAcc = sc.nextLong();
                            sc.nextLine();
                            System.out.print("Enter Amount to Transfer: ");
                            double transferAmount = sc.nextDouble();
                            sc.nextLine();
                            bank.transfer(fromAcc, toAcc, transferAmount);
                            break;

                        case 4:
                            System.out.print("Enter Account Number : ");
                            long accNum = sc.nextLong();
                            double accountBalance = bank.getAccBalance(accNum);
                            if (accountBalance == -1) {
                                System.out.println("Error: Account Number " + accNum + " not found.");
                            } else {
                                System.out.println("Available Balance: " + accountBalance);
                            }
                            break;

                        case 5:
                            System.out.print("Enter Account Number : ");
                            bank.getAccDetails(sc.nextLong());
                            break;

                        case 7:
                            bank.listAccount();
                            break;

                        case 8:
                            System.out.print("Enter Account Number : ");
                            bank.calculateInterest(sc.nextLong());
                            break;

                        default:
                            System.out.println("Invalid choice! Please choose from the given option.");
                    }
                } catch (NullPointerException e) {
                    System.out.println("Error: A null pointer exception occurred. Please check your input and try again." );
                } catch (InsufficientFundException | InvalidAccountException | OverDraftLimitExcededException e) {
                    System.out.println("Error : " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Unexpected error occurred: " + e.getMessage());
                }
            }
        }
    }
}

