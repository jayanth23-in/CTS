USE CognizantSQL;

DROP PROCEDURE IF EXISTS ProcessMonthlyInterest;
DELIMITER $$
CREATE PROCEDURE ProcessMonthlyInterest()
BEGIN
    UPDATE SavingsAccounts
    SET Balance = Balance + (Balance * 0.01);
END $$
DELIMITER ;

CALL ProcessMonthlyInterest();


DROP PROCEDURE IF EXISTS UpdateEmployeeBonus;
DELIMITER $$
CREATE PROCEDURE UpdateEmployeeBonus(
    IN dept_id INT,
    IN bonus_percentage DECIMAL(5,2)
)
BEGIN
    UPDATE EmployeeDetails
    SET Salary = Salary + (Salary * bonus_percentage / 100)
    WHERE DepartmentID = dept_id;
END $$
DELIMITER ;

CALL UpdateEmployeeBonus(3, 10.00);


DROP PROCEDURE IF EXISTS TransferFunds;
DELIMITER $$
CREATE PROCEDURE TransferFunds(
    IN source_account_id INT,
    IN destination_account_id INT,
    IN transfer_amount DECIMAL(12,2)
)
BEGIN
    DECLARE source_balance DECIMAL(12,2);

    SELECT Balance INTO source_balance
    FROM AccountDetails
    WHERE AccountID = source_account_id;

    IF source_balance IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Source account not found';
    ELSEIF source_balance < transfer_amount THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Insufficient balance in source account';
    ELSE
        START TRANSACTION;

        UPDATE AccountDetails
        SET Balance = Balance - transfer_amount
        WHERE AccountID = source_account_id;

        UPDATE AccountDetails
        SET Balance = Balance + transfer_amount
        WHERE AccountID = destination_account_id;

        COMMIT;
    END IF;
END $$
DELIMITER ;

CALL TransferFunds(101, 102, 500.00);