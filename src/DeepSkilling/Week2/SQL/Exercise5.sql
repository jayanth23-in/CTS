USE CognizantSQL;

DROP TRIGGER IF EXISTS UpdateCustomerLastModified;
DELIMITER $$
CREATE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    SET NEW.LastModified = CURDATE();
END $$
DELIMITER ;


DROP TABLE IF EXISTS AuditLog;
CREATE TABLE AuditLog (
    AuditID INT AUTO_INCREMENT PRIMARY KEY,
    TransactionID INT,
    AccountID INT,
    TransactionType VARCHAR(20),
    Amount DECIMAL(12,2),
    LoggedAt DATETIME
);

DROP TRIGGER IF EXISTS LogTransaction;
DELIMITER $$
CREATE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, TransactionType, Amount, LoggedAt)
    VALUES (NEW.TransactionID, NEW.AccountID, NEW.TransactionType, NEW.Amount, NOW());
END $$
DELIMITER ;


DROP TRIGGER IF EXISTS CheckTransactionRules;
DELIMITER $$
CREATE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
BEGIN
    DECLARE current_balance DECIMAL(12,2);

    SELECT Balance INTO current_balance
    FROM AccountDetails
    WHERE AccountID = NEW.AccountID;

    IF NEW.TransactionType = 'Withdrawal' AND NEW.Amount > current_balance THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Withdrawal amount exceeds available balance';
    END IF;

    IF NEW.TransactionType = 'Deposit' AND NEW.Amount <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Deposit amount must be positive';
    END IF;
END $$
DELIMITER ;