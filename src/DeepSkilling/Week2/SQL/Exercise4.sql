USE CognizantSQL;

DROP FUNCTION IF EXISTS CalculateAge;
DELIMITER $$
CREATE FUNCTION CalculateAge(
    date_of_birth DATE
)
RETURNS INT
DETERMINISTIC
BEGIN
    RETURN TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE());
END $$
DELIMITER ;

SELECT
    CustomerID,
    CustomerName,
    DateOfBirth,
    CalculateAge(DateOfBirth) AS Age
FROM CustomerDetails;


DROP FUNCTION IF EXISTS CalculateMonthlyInstallment;
DELIMITER $$
CREATE FUNCTION CalculateMonthlyInstallment(
    loan_amount DECIMAL(12,2),
    annual_interest_rate DECIMAL(5,2),
    loan_duration_years INT
)
RETURNS DECIMAL(12,2)
DETERMINISTIC
BEGIN
    DECLARE monthly_rate DECIMAL(10,8);
    DECLARE total_months INT;
    DECLARE emi DECIMAL(12,2);

    SET monthly_rate = (annual_interest_rate / 12) / 100;
    SET total_months = loan_duration_years * 12;

    IF monthly_rate = 0 THEN
        SET emi = loan_amount / total_months;
    ELSE
        SET emi = (loan_amount * monthly_rate * POWER(1 + monthly_rate, total_months))
                   / (POWER(1 + monthly_rate, total_months) - 1);
    END IF;

    RETURN emi;
END $$
DELIMITER ;

SELECT
    LoanID,
    CustomerID,
    LoanAmount,
    InterestRate,
    LoanDurationYears,
    CalculateMonthlyInstallment(LoanAmount, InterestRate, LoanDurationYears) AS MonthlyInstallment
FROM LoanDetails;


DROP FUNCTION IF EXISTS HasSufficientBalance;
DELIMITER $$
CREATE FUNCTION HasSufficientBalance(
    account_id INT,
    amount DECIMAL(12,2)
)
RETURNS BOOLEAN
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE current_balance DECIMAL(12,2);

    SELECT Balance INTO current_balance
    FROM AccountDetails
    WHERE AccountID = account_id;

    IF current_balance IS NULL THEN
        RETURN FALSE;
    END IF;

    RETURN current_balance >= amount;
END $$
DELIMITER ;

SELECT
    AccountID,
    AccountHolderName,
    Balance,
    HasSufficientBalance(AccountID, 500.00) AS SufficientForWithdrawal
FROM AccountDetails;