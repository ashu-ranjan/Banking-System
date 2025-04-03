// TASK 11.8, 11.9, 11.10

package com.HexBankAssign.app;

import com.HexBankAssign.bean.BankServiceProviderImpl;
import com.HexBankAssign.bean.Account;
import com.HexBankAssign.bean.Customer;

import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankServiceProviderImpl bank = new BankServiceProviderImpl("HexBank", "123 B Street, Chennai");

        while (true){

            System.out.println("\n---> Welcome to HexBank! Create Account to proceed <---");
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
            }
        }
    }
}

