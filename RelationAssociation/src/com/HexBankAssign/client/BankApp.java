package com.HexBankAssign.client;

import com.HexBankAssign.entity.Account;
import com.HexBankAssign.entity.Bank;
import com.HexBankAssign.entity.Customer;

import java.util.Scanner;
import java.util.UUID;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Bank bank = new Bank();
        Account acct = null;

        while (true){

            System.out.println("\n---> Welcome to HexBank! Register to proceed <---");
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
            Account acc = bank.createAccount(customer, accType, balance);
            System.out.println("Account Created. Account Number : " + acc.getAccNumber());


            while (true) {
                System.out.println("\n---> Banking Operation <---");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Transfer");
                System.out.println("4. Get Balance");
                System.out.println("5. Get Account Details");
                System.out.println("6. Register Another Customer");
                System.out.println("7. Exit");

                System.out.print("\nChoose an option to proceed : ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 6)
                    break;
                else if (choice == 7) {
                    System.out.println("Exiting !!! Thankyou for using HexBank Services.");
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
                        System.out.print("Enter from Account : ");
                        long fromAcc = sc.nextLong();
                        System.out.print("Enter to Account : ");
                        long toAcc = sc.nextLong();
                        bank.transfer(fromAcc, toAcc, sc.nextDouble());
                        break;

                    case 4:
                        System.out.print("Enter Account Number : ");
                        System.out.print("Balance : " + bank.getAccountBalance(sc.nextLong()));
                        break;

                    case 5:
                        System.out.print("Enter Account Number : ");
                        bank.getAccountDetails(sc.nextLong());
                        break;

                    default:
                        System.out.println("Invalid choice! Please choose from the given option.");
                }
            }
        }
    }
}
