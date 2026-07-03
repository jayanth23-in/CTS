USE CognizantSQL;

DROP TABLE IF EXISTS ErrorLog;
CREATE TABLE ErrorLog (
    ErrorID INT AUTO_INCREMENT PRIMARY KEY,
    ProcedureName VARCHAR(100),
    ErrorMessage VARCHAR(255),
    LoggedAt DATETIME
);


DROP PROCEDURE IF EXISTS SafeTransferFunds;
DELIMITER $$
CREATE PROCEDURE SafeTransferFunds(
    IN source_account_id INT,
    IN destination_account_id INT,
    IN transfer_amount DECIMAL(12,2)
)
BEGIN
    DECLARE source_balance DECIMAL(12,2);
    DECLARE error_message VARCHAR(255);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1 error_message = MESSAGE_TEXT;
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage, LoggedAt)
        VALUES ('SafeTransferFunds', error_message, NOW());
    END;

    START TRANSACTION;

    SELECT Balance INTO source_balance
    FROM AccountDetails
    WHERE AccountID = source_account_id;

    IF source_balance IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Source account not found';
    ELSEIF source_balance < transfer_amount THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Insufficient balance in source account';
    END IF;

    UPDATE AccountDetails
    SET Balance = Balance - transfer_amount
    WHERE AccountID = source_account_id;

    UPDATE AccountDetails
    SET Balance = Balance + transfer_amount
    WHERE AccountID = destination_account_id;

    COMMIT;
END $$
DELIMITER ;

CALL SafeTransferFunds(101, 102, 500.00);


DROP PROCEDURE IF EXISTS UpdateSalary;
DELIMITER $$
CREATE PROCEDURE UpdateSalary(
    IN emp_id INT,
    IN increase_percentage DECIMAL(5,2)
)
BEGIN
    DECLARE employee_exists INT;
    DECLARE error_message VARCHAR(255);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1 error_message = MESSAGE_TEXT;
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage, LoggedAt)
        VALUES ('UpdateSalary', error_message, NOW());
    END;

    START TRANSACTION;

    SELECT COUNT(*) INTO employee_exists
    FROM EmployeeDetails
    WHERE EmployeeID = emp_id;

    IF employee_exists = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Employee ID does not exist';
    END IF;

    UPDATE EmployeeDetails
    SET Salary = Salary + (Salary * increase_percentage / 100)
    WHERE EmployeeID = emp_id;

    COMMIT;
END $$
DELIMITER ;

CALL UpdateSalary(9999, 10.00);


DROP PROCEDURE IF EXISTS AddNewCustomer;
DELIMITER $$
CREATE PROCEDURE AddNewCustomer(
    IN cust_id INT,
    IN cust_name VARCHAR(100),
    IN cust_email VARCHAR(100)
)
BEGIN
    DECLARE customer_exists INT;
    DECLARE error_message VARCHAR(255);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1 error_message = MESSAGE_TEXT;
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage, LoggedAt)
        VALUES ('AddNewCustomer', error_message, NOW());
    END;

    START TRANSACTION;

    SELECT COUNT(*) INTO customer_exists
    FROM Customers
    WHERE CustomerID = cust_id;

    IF customer_exists > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Customer with this ID already exists';
    END IF;

    INSERT INTO Customers (CustomerID, CustomerName, Email)
    VALUES (cust_id, cust_name, cust_email);

    COMMIT;
END $$
DELIMITER ;

CALL AddNewCustomer(501, 'Jordan Lee', 'jordan.lee@example.com');