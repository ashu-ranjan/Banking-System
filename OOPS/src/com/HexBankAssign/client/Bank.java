package com.HexBankAssign.client;

import com.HexBankAssign.entity.*;
import java.util.Scanner;


public class Bank {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Customer customer = new Customer(); // creating object of Customer class

        // ADDING NEW CUSTOMER
        System.out.println("\n---> Enter Customer Details <--- ");

        System.out.print("\nEnter Customer ID : " );
        customer.setCustomerID(sc.nextInt());

        sc.nextLine();

        System.out.print("Enter First Name : ");
        customer.setFirstName(sc.nextLine());

        System.out.print("Enter Last Name : ");
        customer.setLastName(sc.nextLine());

        System.out.print("Enter email ID : ");
        customer.setEmail(sc.nextLine());

        System.out.print("Enter Phone Number : ");
        customer.setPhoneNumber(sc.nextLong());

        sc.nextLine();

        System.out.print("Enter Address : ");
        customer.setAddress(sc.nextLine());


        Account account = new Account(); // creating object of Account class

        // ADDING NEW BANK ACCOUNT
        System.out.println("\n---> Enter Account Details <---");

        System.out.print("Enter Account Number : ");
        account.setAccountNumber(sc.nextLong());

        sc.nextLine();

        System.out.print("Enter Account Type (Saving/Current) : ");
        account.setAccountType(sc.nextLine());

        System.out.print("Enter Account Balance : ₹");
        account.setAccountBalance(sc.nextDouble());


        customer.customerInfo(); // displaying customer information
        account.accountInfo(); // displaying account information

        boolean running = true;

        // RUNNING TRANSACTION FOR THE GIVEN CUSTOMER

        while (running){

            System.out.println("\n---> Banking Operations <---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Calculate Interest (Only for Savings)");
            System.out.println("4. Show Account Details");
            System.out.println("5. Exit");
            System.out.print("\nEnter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit : ₹");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw : ₹");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.print("Congratulations!! ");
                    account.calcInterest();
                    break;

                case 4:
                    System.out.println("Updated Account Info.");
                    account.accountInfo();
                    break;

                case 5:
                    System.out.println("Exiting !!! Thankyou for using our banking services.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid Choice ! Please choose from the above options.");
                    break;

            }
        }
        sc.close();
    }
}
