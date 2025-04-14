package com.HexBankAssignHMB.app;


import com.HexBankAssignHMB.dao.BankServiceProviderImpl;
import com.HexBankAssignHMB.entity.Account;
import com.HexBankAssignHMB.entity.Customer;
import com.HexBankAssignHMB.entity.Transaction;
import com.HexBankAssignHMB.exception.InvalidAccountException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class HMBApp {
    private static BankServiceProviderImpl bankService = BankServiceProviderImpl.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InvalidAccountException {
        while (true) {
            System.out.println("\n------ Welcome to HM Bank ------");
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Get Balance");
            System.out.println("5. Transfer Money");
            System.out.println("6. Get Account Details");
            System.out.println("7. List All Accounts");
            System.out.println("8. Get Transactions");
            System.out.println("9. Exit");
            System.out.print("\nEnter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    getBalance();
                    break;
                case 5:
                    transfer();
                    break;
                case 6:
                    getAccountDetails();
                    break;
                case 7:
                    listAccounts();
                    break;
                case 8:
                    getTransactions();
                    break;
                case 9:
                    System.out.println("Exiting the banking system. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter First Name: ");
        String firstName = scanner.next();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.next();
        LocalDate dateOfBirth = getValidDate(scanner);
        System.out.print("Enter Email: ");
        String email = scanner.next();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.next();
        System.out.print("Enter Address: ");
        scanner.nextLine();
        String address = scanner.nextLine();

        Customer customer = new Customer(firstName, lastName, email, phone, address, dateOfBirth);

        System.out.println("Select Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.println("3. Zero Balance Account");
        System.out.print("Enter choice: ");
        int accChoice = scanner.nextInt();

        float initialBalance = 0;
        if (accChoice == 1) {
            System.out.print("Enter Initial Balance (Min: 500): ");
            initialBalance = scanner.nextFloat();
        } else if (accChoice == 2) {
            System.out.print("Enter Initial Balance: ");
            initialBalance = scanner.nextFloat();
        }

        String accountType = (accChoice == 1) ? "savings" :
                (accChoice == 2) ? "current" : "zero-balance";

        Account newAccount = bankService.createAcc(customer, accountType, initialBalance);
        if (newAccount == null) {
            System.out.println("Account Creation Failed.");
        }

    }

    private static void deposit() {
        System.out.print("Enter Account Number: ");
        long accNumber = scanner.nextLong();
        System.out.print("Enter Amount to Deposit: ");
        float amount = scanner.nextFloat();

        float newBalance = bankService.deposit(accNumber, amount);
        if (newBalance < 0) {
            System.out.println("Deposit Failed! Check account details.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter Account Number: ");
        long accNumber = scanner.nextLong();
        System.out.print("Enter Amount to Withdraw: ");
        float amount = scanner.nextFloat();

        float newBalance = bankService.withdraw(accNumber, amount);
        if (newBalance < 0) {
            System.out.println("Withdrawal Failed! Insufficient funds or incorrect details.");
        }
    }

    private static void getBalance() {
        System.out.print("Enter Account Number: ");
        long accNumber = scanner.nextLong();
        float balance = bankService.getAccBalance(accNumber);
        System.out.println("Current Available Balance: " + balance);
    }

    private static void transfer() throws InvalidAccountException {
        System.out.print("Enter Sender Account Number: ");
        long fromAcc = scanner.nextLong();
        System.out.print("Enter Receiver Account Number: ");
        long toAcc = scanner.nextLong();
        System.out.print("Enter Amount to Transfer: ");
        float amount = scanner.nextFloat();

        boolean success = bankService.transfer(fromAcc, toAcc, amount);
        if (success) {
            System.out.println("Transfer Successfully Done!");
        } else {
            System.out.println("Transfer Failed! Check account details or balance.");
        }
    }

    private static void getAccountDetails() {
        System.out.print("Enter Account Number: ");
        long accNumber = scanner.nextLong();

        Account account = bankService.getAccDetails(accNumber);
        if (account != null) {
            System.out.println("Account Details:\n" + account);
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void listAccounts() {
        List<Account> accounts = bankService.listAccount();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("\n------ All Accounts ------");
            for (Account acc : accounts) {
                System.out.println(acc);
            }
        }
    }

    private static void getTransactions() {
        System.out.print("Enter Account Number: ");
        long accNumber = scanner.nextLong();
        System.out.print("Enter From Date (yyyy-MM-dd): ");
        String fromDateStr = scanner.next();
        System.out.print("Enter To Date (yyyy-MM-dd): ");
        String toDateStr = scanner.next();

        try {
            LocalDateTime fromDate = LocalDate.parse(fromDateStr).atStartOfDay();
            LocalDateTime toDate = LocalDate.parse(toDateStr).atTime(23, 59, 59);

            List<Transaction> transactions = bankService.getTransactions(accNumber, fromDate, toDate);
            if (transactions.isEmpty()) {
                System.out.println("No transactions found in the given date range.");
            } else {
                System.out.println("\n------ Transaction History ------");
                for (Transaction txn : transactions) {
                    System.out.println(txn);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid Date Format! Please enter in yyyy-MM-dd format.");
        }
    }

    private static LocalDate getValidDate(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateOfBirth = null;

        while (dateOfBirth == null) {
            System.out.print("Enter Date of Birth (DD-MM-YYYY): ");
            String dobInput = scanner.next();
            try {
                dateOfBirth = LocalDate.parse(dobInput, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please enter in DD-MM-YYYY format.");
            }
        }
        return dateOfBirth;
    }
}


