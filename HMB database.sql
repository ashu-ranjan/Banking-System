
-- Create and use database
CREATE DATABASE hmb;
USE hmb;

-- Create sequences table for ID generation
CREATE TABLE hmb_sequences (
    sequence_name VARCHAR(50) PRIMARY KEY,
    next_val BIGINT NOT NULL
);

-- Initialize sequences
INSERT INTO hmb_sequences VALUES ('customer_seq', 1)
	ON DUPLICATE KEY UPDATE next_val = next_val;
INSERT INTO hmb_sequences VALUES ('transaction_seq', 1)
	ON DUPLICATE KEY UPDATE next_val = next_val;
    
SET GLOBAL log_bin_trust_function_creators = 1;


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

-- Trigger for customer ID generation
DELIMITER $$
CREATE TRIGGER before_customer_insert
BEFORE INSERT ON Customers
FOR EACH ROW
BEGIN
    IF NEW.customer_id IS NULL THEN
        SET NEW.customer_id = CONCAT('CUST0', LPAD(next_val('customer_seq'), 4, '0'));
    END IF;
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

-- Create indexes for better performance
CREATE INDEX idx_accounts_customer ON accounts(customer_id);
CREATE INDEX idx_transactions_account ON transactions(account_id);
CREATE INDEX idx_transactions_date ON transactions(trans_date);

-- Remove the default constraint first if it exists
ALTER TABLE Customers MODIFY customer_id VARCHAR(15);

Drop trigger before_customer_insert;

-- Then add a trigger for ID generation
DELIMITER $$
CREATE TRIGGER before_customer_insert
BEFORE INSERT ON Customers
FOR EACH ROW
BEGIN
    DECLARE next_val INT;
    
    -- Get next sequence value
    SELECT next_val INTO next_val FROM hmb_sequences 
    WHERE sequence_name = 'customer_seq' FOR UPDATE;
    
    -- Update sequence
    UPDATE hmb_sequences SET next_val = next_val + 1 
    WHERE sequence_name = 'customer_seq';
    
    -- Set customer_id
    SET NEW.customer_id = CONCAT('CUST0', LPAD(next_val, 4, '0'));
END$$
DELIMITER ;

SHOW TRIGGERS LIKE 'Customers';
SHOW TRIGGERS LIKE 'transactions';

SELECT * FROM Customers;
select * from accounts;
SELECT * FROM Customers WHERE phone = '9087654321';

SHOW CREATE TABLE accounts;

SET GLOBAL general_log = 'ON';
SET GLOBAL log_output = 'TABLE';

SELECT * FROM mysql.general_log ORDER BY event_time DESC LIMIT 10;

SHOW TRIGGERS LIKE 'accounts';

SELECT * FROM Customers WHERE customer_id = 'CUST00003';
SHOW CREATE TABLE accounts;
SHOW TRIGGERS LIKE 'accounts';

select * from accounts;

SHOW COLUMNS FROM accounts LIKE 'acc_type';

-- SHOW FUNCTION STATUS WHERE Db = DATABASE();
-- SELECT * FROM hmb_sequences;

-- SELECT next_val('customer_seq');
-- SELECT next_val('transaction_seq');

INSERT INTO Customers (first_name, last_name, dob, email, phone, address)
VALUES ('Test', 'User', '1990-01-01', 'test1@example.com', '1234567890', 'Test Address');

drop table customers;

SELECT CONSTRAINT_NAME 
FROM information_schema.KEY_COLUMN_USAGE 
WHERE TABLE_NAME = 'accounts' AND COLUMN_NAME = 'customer_id';


ALTER TABLE accounts DROP FOREIGN KEY fk_accounts_customer;


-- 1. Drop the existing primary key constraint if it exists
ALTER TABLE Customers DROP PRIMARY KEY;

-- 2. Modify the customer_id column to be auto-incremented
ALTER TABLE Customers MODIFY customer_id VARCHAR(10) PRIMARY KEY;

-- 3. Add a view or computed column for the formatted ID if needed
CREATE VIEW CustomerView AS 
SELECT CONCAT('CUST0', LPAD(customer_id, 4, '0')) AS formatted_id,
       first_name, last_name, dob, email, phone, address
FROM Customers;

ALTER TABLE customers MODIFY customer_id VARCHAR(10) NOT NULL;
ALTER TABLE accounts MODIFY customer_id VARCHAR(10) NOT NULL;

ALTER TABLE accounts 
ADD CONSTRAINT fk_accounts_customer 
FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE Customers MODIFY customer_id VARCHAR(10) primary KEY;

ALTER TABLE Customers 
MODIFY customer_id VARCHAR(10) NOT NULL DEFAULT 'CUST00000' primary KEY;

-- DROP TRIGGER before_customer_insert;

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

select * from customers;
select * from accounts;
select * from transactions;
