-- creation of database

CREATE DATABASE hmb;
USE hmb;

-- creation of tables

CREATE TABLE hmb_sequences (
    sequence_name VARCHAR(50) PRIMARY KEY,
    next_val BIGINT NOT NULL
);

-- Initialize sequences

INSERT INTO hmb_sequences VALUES ('customer_seq', 1)
	ON DUPLICATE KEY UPDATE next_val = next_val;
INSERT INTO hmb_sequences VALUES ('transaction_seq', 1)
	ON DUPLICATE KEY UPDATE next_val = next_val;
    
SET GLOBAL log_bin_trust_function_creators = 1; -- to create functions

-- Function to get next sequence value

DELIMITER $$
CREATE FUNCTION next_val(seq_name VARCHAR(50))
RETURNS BIGINT
DETERMINISTIC
MODIFIES SQL DATA

BEGIN
    DECLARE next_value BIGINT;
    
    UPDATE hmb_sequences 
    SET next_val = next_val + 1 
    WHERE sequence_name = seq_name;
    
    SELECT next_val INTO next_value 
    FROM hmb_sequences 
    WHERE sequence_name = seq_name;
    
    RETURN next_value;
END $$
DELIMITER ;

-- Customers table with auto-generated CUST0#### IDs

CREATE TABLE IF NOT EXISTS Customers (
    customer_id varchar(10) primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    dob date not null,
    email varchar(50) unique not null,
    phone varchar(50) unique not null,
    address text not null 
);

-- Trigger for Customer ID generation

DELIMITER $$
CREATE TRIGGER before_customer_insert
BEFORE INSERT ON Customers
FOR EACH ROW
BEGIN
    DECLARE next_id INT;
    SELECT IFNULL(MAX(CAST(SUBSTRING(customer_id, 5) AS UNSIGNED)), 0) + 1 
    INTO next_id FROM Customers;
    SET NEW.customer_id = CONCAT('CUST', LPAD(next_id, 4, '0'));
END $$
DELIMITER ;

-- Accounts table with auto-increment IDs

CREATE TABLE IF NOT EXISTS accounts(
    account_id bigint primary key auto_increment,
    customer_id varchar(10) not null,
    acc_type enum('savings', 'current', 'zero-balance') not null,
    acc_balance decimal(20,2) not null default 0.00,
    created_at datetime default current_timestamp,
    foreign key (customer_id) references Customers(customer_id)
    on delete cascade
    on update cascade,
    check (acc_balance >= 0),
    -- Additional check for savings account minimum balance
    check (
        (acc_type != 'savings') OR 
        (acc_type = 'savings' AND acc_balance >= 500)
    )
);

-- Transactions table with auto-generated TRXYYYY##### IDs

CREATE TABLE IF NOT EXISTS transactions(
    transaction_id varchar(15) primary key,
    account_id bigint not null,
    trans_type enum('deposit', 'withdraw', 'transfer') not null,
    trans_amount decimal(20,2) not null,
    trans_desc text,
    trans_date datetime default current_timestamp,
    foreign key (account_id) references accounts(account_id)
    on delete cascade
    on update cascade,
    check (trans_amount > 0)
);

-- Trigger for transaction ID generation

DELIMITER $$
CREATE TRIGGER before_transaction_insert
BEFORE INSERT ON transactions
FOR EACH ROW
BEGIN
    IF NEW.transaction_id IS NULL THEN
        SET NEW.transaction_id = CONCAT('TRX', YEAR(CURDATE()), LPAD(next_val('transaction_seq'), 5, '0'));
    END IF;
END $$
DELIMITER ;

-- Add a view or computed column for the formatted ID if needed

CREATE VIEW CustomerView AS 
SELECT CONCAT('CUST0', LPAD(customer_id, 4, '0')) AS formatted_id,
       first_name, last_name, dob, email, phone, address
FROM Customers;

-- Create indexes for better performance

CREATE INDEX idx_accounts_customer ON accounts(customer_id);
CREATE INDEX idx_transactions_account ON transactions(account_id);
CREATE INDEX idx_transactions_date ON transactions(trans_date);

-- view tables

select * from customers;
select * from accounts;
select * from transactions;
