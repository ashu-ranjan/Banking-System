// TASK 9.3 (Bank.java)

package com.HexBankAssign.client;

import com.HexBankAssign.entity.*;
import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankAccount account = null;

        System.out.println("\n---> Bank Account Types <---");
        System.out.println("1. Saving Account");
        System.out.println("2. Current Account");

        System.out.print("\nChoose an option : ");
        int choice = sc.nextInt();

        System.out.print("\nEnter Account Number : ");
        long accNumber = sc.nextLong();
        sc.nextLine();
        System.out.print("Enter Customer Name : ");
        String customerName = sc.nextLine();
        System.out.print("Initial Balance : ");
        double balance = sc.nextDouble();

        switch (choice){
            case 1:
                account = new SavingAccount(accNumber, customerName,balance );
                break;

            case 2:
                account = new CurrentAccount(accNumber, customerName, balance);
                break;

            default:
                System.out.println("Invalid Choice!");
                System.exit(0);
        }

        while (true){
            System.out.println("\n---> Banking Transaction Operations <---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Calculate Interest");
            System.out.println("4. Display Account Details");
            System.out.println("5. Exit");

            System.out.print("\nChoose an option : ");
            int option = sc.nextInt();

            switch (option){
                case 1:
                    System.out.print("Enter Deposit amount : ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Enter Withdraw amount : ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 3:
                    account.calcInterest();
                    break;

                case 4:
                    account.displayInfo();
                    break;

                case 5:
                    System.out.println("Exiting !!! Thankyou for choosing our bank service.");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option chosen!");
            }
        }
    }
}
