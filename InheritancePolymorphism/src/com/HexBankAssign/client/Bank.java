
// TASK 8.3 (Bank Class)

package com.HexBankAssign.client;

import com.HexBankAssign.entity.Account;
import com.HexBankAssign.entity.CurrentAccount;
import com.HexBankAssign.entity.SavingAccount;

import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Account account = null;

        System.out.println("Choose Account Type : ");
        System.out.println("1. Saving Account.");
        System.out.println("2. Current Account.");
        System.out.print("\nChoose an option to proceed : ");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Account Number : ");
        long accNumber = sc.nextLong();
        sc.nextLine();

        System.out.print("Enter Initial Balance : ");
        double accBalance = sc.nextDouble();
        sc.nextLine();

        switch (choice){
            case 1:
                account = new SavingAccount(accNumber,accBalance);
                break;

            case 2:
                account = new CurrentAccount(accNumber, accBalance);
                break;

            default:
                System.out.println("Invalid Choice");
                System.exit(0);

        }

        boolean running = true;

        while (running){
            System.out.println("\n---> Banking Operations <---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Calculate Interest (Only for Savings)");
            System.out.println("4. Show Account Details");
            System.out.println("5. Exit");
            System.out.print("\nEnter your choice : ");

            int ops = sc.nextInt();
            sc.nextLine();

            switch (ops){
                case 1:
                    System.out.print("Enter deposit amount : ₹");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount : ₹");
                    double withdrawalAmount = sc.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;

                case 3:
                    account.calcInterest();
                    break;

                case 4:
                    account.accInfo();
                    break;

                case 5:
                    System.out.println("Exiting... Thank you for using HexBank.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        sc.close();
    }
}
