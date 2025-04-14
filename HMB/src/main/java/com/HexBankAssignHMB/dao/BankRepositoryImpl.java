package com.HexBankAssignHMB.dao;

import com.HexBankAssignHMB.entity.*;
import com.HexBankAssignHMB.util.HMBConstants;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankRepositoryImpl implements IBankRepository {

    private Connection connection;

    public BankRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Account createAcc(Customer customer, String accType, float accBalance) {
        try {
            connection.setAutoCommit(false);

            String generatedCustomerId = insertCustomer(customer);
            if (generatedCustomerId == null) {
                System.out.println("[ERROR] Failed to create customer");
                connection.rollback();
                return null;
            }

            Account account = insertAccount(generatedCustomerId, accType, accBalance);
            if (account == null) {
                System.out.println("[ERROR] Failed to create account");
                connection.rollback();
                return null;
            }
            connection.commit();

            return account;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
            System.out.println("Error creating account: " + e.getMessage());
            return null;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Failed to reset auto-commit: " + e.getMessage());
            }
        }
    }

    private String insertCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customers (first_name, last_name, dob, email, phone, address) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setDate(3, java.sql.Date.valueOf(customer.getDateOfBirth()));
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPhoneNumber());
            stmt.setString(6, customer.getAddress());


            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating customer failed, no rows affected.");
            }

            String selectSql = "SELECT customer_id FROM Customers WHERE email = ?";
            try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
                selectStmt.setString(1, customer.getEmail());
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        String customerId = rs.getString("customer_id");
                        System.out.println("Successfully created Customer ID for " + customer.getFirstName()
                                + " " + customer.getLastName() + " is " + customerId);
                        customer.setCustomerId(customerId);
                        return customerId;
                    }
                }
            }
            throw new SQLException("Failed to retrieve customer ID after insertion");
        } catch (SQLException e) {
            System.out.println("[SQL ERROR] Customer creation failed: " + e.getMessage());
            throw e;
        }
    }

    private Account insertAccount(String customerId, String accType, float balance) throws SQLException {

        String sql = HMBConstants.INSERT_INTO_ACC;
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customerId);
            stmt.setString(2, accType.toLowerCase());
            stmt.setFloat(3, balance);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating account failed, no rows affected.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    long accountId = rs.getLong(1);

                    Customer customer = getCustomerById(customerId);
                    if (customer == null) {
                        throw new SQLException("Customer not found for ID: " + customerId);
                    }

                    switch (accType.toLowerCase()) {
                        case "savings":
                            SavingsAccount savingsAccount = new SavingsAccount(accountId, balance, customer);
                            System.out.println("Savings account created successfully. Your Account Number is " + accountId);
                            return savingsAccount;
                        case "current":
                            CurrentAccount currentAccount = new CurrentAccount(accountId, customer, balance);
                            System.out.println("Current account created successfully. Your Account Number is " + accountId);
                            return currentAccount;
                        case "zero-balance":
                            ZeroBalanceAccount zba = new ZeroBalanceAccount(accountId, customer, balance);
                            System.out.println("Zero balance account created successfully. Your Account Number is " + accountId);
                            return zba;
                        default:
                            throw new IllegalArgumentException("Invalid account type: " + accType);
                    }
                }
            }
            throw new SQLException("Failed to retrieve generated account ID");
        }
    }


    @Override
    public List<Account> listAccount() {
        List<Account> accounts = new ArrayList<>();
        String sql = HMBConstants.VIEW_ACC_QRY;

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long accountId = rs.getLong("account_id");
                String customerId = rs.getString("customer_id");
                String accType = rs.getString("acc_type");
                float balance = rs.getFloat("acc_balance");

                Customer customer = getCustomerById(customerId);
                Account account = createAccountObject(accountId, accType, balance, customer);
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Error listing accounts: " + e.getMessage());
        }
        return accounts;
    }

    private Account createAccountObject(long accNumber, String accType, float balance, Customer customer) {
        switch (accType.toLowerCase()) {
            case "savings":
                return new SavingsAccount(accNumber, balance, customer);
            case "current":
                return new CurrentAccount(accNumber,  customer, balance);
            case "zero-balance":
                return new ZeroBalanceAccount(accNumber,  customer, balance);
            default:
                throw new IllegalArgumentException("Invalid account type: " + accType);
        }
    }

    @Override
    public float getAccBalance(long accNumber) {
        if (accNumber == -1) return -1;

        String sql = HMBConstants.BAL_BY_ACC_ID;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, accNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getFloat("acc_balance");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting balance: " + e.getMessage());
        }
        return -1;
    }


    @Override
    public float deposit(long accNumber, float amount) {

        if (amount <= 0) {
            System.out.println("Invalid deposit amount: " + amount + " for account: " + accNumber);
            return -1;
        }

        String sql = HMBConstants.DEPOSIT_BY_ACC_ID;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, amount);
            stmt.setLong(2, accNumber);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                float newBalance = getAccBalance(accNumber);

                Account account = getAccDetails(accNumber);
                if (account != null) {
                    Transaction transaction = new Transaction(
                            account,
                            "DEPOSIT",
                            amount,
                            "Cash deposit to account " + accNumber
                    );
                    logTransaction(transaction);
                } else {
                    System.out.println("Account not found: " + accNumber);
                }

                System.out.println("Deposit successful. New Available Balance: " + newBalance + " for account: " + accNumber);
                return newBalance;
            }
        } catch (SQLException e) {
            System.out.println("Error depositing to account " + accNumber + ": " + e.getMessage());
        }
        return -1;
    }

    @Override
    public float withdraw(long accNumber, float amount) {

        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount: " + amount + " for account: " + accNumber);
            return -1;
        }

        Account account = getAccDetails(accNumber);
        if (account == null) {
            System.out.println("Account not found: " + accNumber);
            return -1;
        }

        String sql;
        if (account instanceof SavingsAccount) {
            sql = "UPDATE accounts SET acc_balance = acc_balance - ? " +
                    "WHERE account_id = ? AND acc_balance - ? >= 500";
        } else {
            sql = HMBConstants.WITHDRAW_BY_ACC_ID;
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, amount);
            stmt.setLong(2, accNumber);
            if (account instanceof SavingsAccount) {
                stmt.setFloat(3, amount);
            }

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                float newBalance = getAccBalance(accNumber);

                Transaction transaction = new Transaction(
                        account,
                        "WITHDRAW",
                        amount,
                        "Cash withdrawal from " + accNumber +
                                (account instanceof SavingsAccount ? " (Savings)" : "")
                );
                logTransaction(transaction);

                System.out.println("Withdrawal successful. New Available Balance: " +
                        newBalance + " for account: " + accNumber);
                return newBalance;
            } else {
                System.out.println("Withdrawal failed - insufficient funds or minimum balance violation for: " + accNumber);
            }
        } catch (SQLException e) {
            System.out.println("Error withdrawing from account " + accNumber + ": " + e.getMessage());
        }
        return -1;
    }

    @Override
    public boolean transfer(long fromAccNumber, long toAccNumber, float amount) {

        try {
            connection.setAutoCommit(false);

            float sourceBalance = withdraw(fromAccNumber, amount);
            if (sourceBalance < 0) {
                connection.rollback();
                return false;
            }

            float targetBalance = deposit(toAccNumber, amount);
            if (targetBalance < 0) {
                connection.rollback();
                return false;
            }

            Account sourceAccount = getAccDetails(fromAccNumber);
            if (sourceAccount != null) {
                Transaction transaction = new Transaction(
                        sourceAccount,
                        "TRANSFER",
                        amount,
                        "Transfer to account " + toAccNumber
                );
                logTransaction(transaction);
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
            System.out.println("Transfer failed: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }

    @Override
    public Account getAccDetails(long accNumber) {
        if (accNumber == -1) return null;

        String sql = HMBConstants.VIEW_ACC_BY_ID;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, accNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long dbAccountId = rs.getLong("account_id");
                    String accType = rs.getString("acc_type");
                    float balance = rs.getFloat("acc_balance");
                    String customerId = rs.getString("customer_id");

                    Customer customer = getCustomerById(customerId);
                    return createAccountObject(dbAccountId, accType, balance, customer);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting account details for " + accNumber + ": " + e.getMessage());
        }
        return null;
    }


    @Override
    public float calculateInterest(long accNumber, int years) {
        if (accNumber == -1) return -1;

        try (PreparedStatement st = connection.prepareStatement(HMBConstants.GET_ACC_TYPE_BY_ACC_ID_INT)) {
            st.setLong(1, accNumber);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                float balance = rs.getFloat("acc_balance");
                String accType = rs.getString("acc_type");

                if (!accType.equalsIgnoreCase("Savings")) {
                    System.out.println("Interest calculation only applies to savings accounts. Account: " + accNumber);
                    return -1;
                }

                float interestEarned = balance * HMBConstants.INTEREST_RATE * years;
                System.out.println(String.format(
                        "Interest Earned for account %s over %d years: Rs.%.2f",
                        accNumber, years, interestEarned
                ));
                return interestEarned;
            }
        } catch (SQLException e) {
            System.out.println("Error calculating interest for account " + accNumber + ": " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }


    private Customer getCustomerById(String customerId) throws SQLException {
        String sql = HMBConstants.GET_CUSTOMER_BY_ID;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getDate("dob").toLocalDate()
                    );
                }
            }
        }
        return null;
    }

    @Override
    public void logTransaction(Transaction transaction) {
        String sql = HMBConstants.INSERT_INTO_TRANS;
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            long accountId = transaction.getAccount().getAccNumber();

            stmt.setLong(1, accountId);
            stmt.setString(2, transaction.getTransactionType());
            stmt.setDouble(3, transaction.getTransAmount());
            stmt.setString(4, transaction.getDescription());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        String generatedId = rs.getString(1);
                        transaction.setTransactionId(generatedId);

                        System.out.println("Transaction logged successfully. ID: " + generatedId);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error logging transaction: " + e.getMessage());
            e.printStackTrace();
            System.err.println("Failed transaction details:");
            System.err.println("Account: " + transaction.getAccount().getAccNumber());
            System.err.println("Type: " + transaction.getTransactionType());
            System.err.println("Amount: " + transaction.getTransAmount());
            System.err.println("Description: " + transaction.getDescription());
        }
    }

    @Override
    public List<Transaction> getTransactions(long accNumber, LocalDateTime fromDate, LocalDateTime toDate) {
        List<Transaction> transactions = new ArrayList<>();
        if (accNumber == -1) return transactions;

        String sql = HMBConstants.VIEW_TRANS_BTW_DATE;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, accNumber);
            stmt.setTimestamp(2, Timestamp.valueOf(fromDate));
            stmt.setTimestamp(3, Timestamp.valueOf(toDate));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String transId = rs.getString("transaction_id");
                    String transType = rs.getString("trans_type");
                    double amount = rs.getDouble("trans_amount");
                    Timestamp timestamp = rs.getTimestamp("trans_date");
                    String description = rs.getString("trans_desc");

                    Account account = getAccDetails(accNumber);
                    if (account != null) {
                        Transaction transaction = new Transaction(
                                account,
                                transType,
                                (float) amount,
                                description
                        );
                        transaction.setTransactionId(transId);
                        transaction.setTransactionTime(timestamp.toLocalDateTime());
                        transactions.add(transaction);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting transactions for account " + accNumber +
                    " between " + fromDate + " and " + toDate + ": " + e.getMessage());
        }
        return transactions;
    }
}





