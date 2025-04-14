package com.HexBankAssignHMB.entity;

import com.HexBankAssignHMB.exception.InvalidTransactionException;

import java.time.LocalDateTime;

public class Transaction {

    private String transactionId;
    private Account account;
    private String transactionType;
    private double transAmount;
    private LocalDateTime transactionTime;
    private String description;

    public Transaction(Account account, String transactionType, float transAmount, String description) {
        try {
            if (transAmount <= 0) {
                throw new InvalidTransactionException("Transaction Amount must be positive.");
            }
            this.account = account;
            this.transactionType = transactionType;
            this.transAmount = transAmount;
            this.transactionTime = LocalDateTime.now();
            this.description = description;
        } catch (InvalidTransactionException e) {
            System.out.println("Transaction Error: " + e.getMessage());
            throw new RuntimeException("Failed to initialize Transaction due to invalid input.", e);
        }
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Account getAccount() {
        return account;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getTransAmount() {
        return transAmount;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format(
                "\n%-20s : %s" +
                        "\n%-20s : %s" +
                        "\n%-20s : ₹%.2f" +
                        "\n%-20s : %s" +
                        "\n%-20s : %s",
                "Transaction ID", transactionId,
                "Transaction Type", transactionType,
                "Amount", transAmount,
                "Description", description,
                "Transaction Time", transactionTime
        );
    }
}
