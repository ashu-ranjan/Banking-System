package com.HexBankAssignHMB.util;

public class HMBConstants {

    public static final String ACC_NOT_FOUND = "Account Not Found";
    public static final String ACC_EXISTS = "Account Already Exists";
    public static final String DELIMITER = ",";
    public static final String DB_FILE_NAME = "HMB/src/main/resources/HMBdb.properties";
    public static final String DB_DRIVER = "db.driver";
    public static final String DB_URL = "db.url";
    public static final String DB_USER = "db.user";
    public static final String DB_PASSWORD = "db.password";
    public static final String CANNOT_OPEN_CONNECTION = "Cannot Open Connection";
    public static final float INTEREST_RATE = 0.04f;
    public static final float OVERDRAFT_LIMIT = 5000.0f;
    public static final double MIN_BALANCE = 500;

    // SQL Queries

    public static final String INSERT_INTO_ACC = "INSERT INTO Accounts (customer_id, acc_type, acc_balance) VALUES (?, ?, ?)";
    public static final String INSERT_INTO_CUST = "INSERT INTO Customers (first_name, last_name, dob, email, phone, address) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    public static final String INSERT_INTO_TRANS = "INSERT INTO Transactions (account_id, trans_type, trans_amount, trans_desc) VALUES (?, ?, ?, ?)";
    public static final String GET_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE customer_id = ?";

    public static final String VIEW_ACC_QRY = "SELECT * FROM Accounts";
    public static final String VIEW_ACC_BY_ACC_ID_QRY = "SELECT * FROM Accounts WHERE account_id = ?";

    public static final String INSERT_INTO_TRANS_DEPOSIT = "INSERT INTO Transactions (account_id, trans_type, trans_amount,trans_desc, trans_date) VALUES (?, 'Deposit', ?, ?, NOW())";
    public static final String INSERT_INTO_TRANS_WITHDRAW = "INSERT INTO Transactions (account_id, trans_type, trans_amount,trans_desc, trans_date) VALUES (?, 'Withdraw', ?, ?, NOW())";
    public static final String INSERT_INTO_TRANS_TRANSFER = "INSERT INTO Transactions (account_id, trans_type, trans_amount,trans_desc, trans_date) VALUES (?, 'Transfer', ?, ?, NOW())";
    public static final String VIEW_TRANS_QRY = "SELECT * FROM Transactions";
    public static final String VIEW_TRANS_BY_ACC_ID_QRY = "SELECT * FROM Transactions WHERE account_id = ?";
    public static final String VIEW_TRANS_BY_DATE_QRY = "SELECT * FROM Transactions WHERE account_id = ? AND trans_date BETWEEN ? AND ?";

    public static final String BAL_BY_ACC_ID = "SELECT acc_balance FROM Accounts WHERE account_id = ?";

    public static final String DEPOSIT_BY_ACC_ID = "UPDATE Accounts SET acc_balance = acc_balance + ? WHERE account_id = ?";
    public static final String WITHDRAW_BY_ACC_ID = "UPDATE Accounts SET acc_balance = acc_balance - ? WHERE account_id = ?";
    public static final String UPDATE_WITHDRAW_BAL_BY_ACC_ID = "UPDATE Accounts SET acc_balance = acc_balance - ? WHERE account_id = ? AND acc_balance >= ?";

    public static final String TRANSFER_BY_ACC_ID = "UPDATE Accounts SET acc_balance = acc_balance - ? WHERE account_id = ?";
    public static final String TRANSFER_BY_ACC_ID_2 = "UPDATE Accounts SET acc_balance = acc_balance + ? WHERE account_id = ?";
    public static final String VIEW_ACC_BY_ID = "SELECT * FROM Accounts WHERE account_id = ?";
    public static final String VIEW_ACC_BY_TYPE = "SELECT * FROM Accounts WHERE acc_type = ?";
    public static final String VIEW_TRANS_BTW_DATE = "SELECT * FROM Transactions WHERE account_id = ? AND trans_date BETWEEN ? AND ?";
    public static final String VIEW_TRANS_BY_DATE = "SELECT * FROM Transactions WHERE account_id = ? AND trans_date = ?";
    public static final String VIEW_TRANS_BY_ACC_ID = "SELECT * FROM Transactions WHERE account_id = ?";
    public static final String GET_ACC_TYPE_BAL_BY_ACC_ID = "SELECT acc_type, acc_balance FROM Accounts WHERE account_id = ?";
    public static final String UPDATE_BAL_INTEREST_BY_ACC_ID = "UPDATE Accounts SET acc_balance = ? WHERE account_id = ?";
    public static final String GET_ACC_TYPE_BY_ACC_ID_INT = "SELECT acc_balance, acc_type FROM Accounts WHERE account_id = ?";

    // Param Index

    public static final int PARAM_INDEX_1 = 1;
    public static final int PARAM_INDEX_2 = 2;
    public static final int PARAM_INDEX_3 = 3;
    public static final int PARAM_INDEX_4 = 4;
    public static final int PARAM_INDEX_5 = 5;
    public static final int PARAM_INDEX_6 = 6;
    public static final int PARAM_INDEX_7 = 7;
    public static final int PARAM_INDEX_8 = 8;
    public static final int PARAM_INDEX_9 = 9;
    public static final int PARAM_INDEX_10 = 10;



}
