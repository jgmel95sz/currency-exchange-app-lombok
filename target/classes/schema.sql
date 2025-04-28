CREATE TABLE rates (
    id INT PRIMARY KEY AUTO_INCREMENT,
    source_currency VARCHAR(3),
    target_currency VARCHAR(3),
    rate DOUBLE
);

CREATE TABLE audit_logs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    original_amount DOUBLE,
    converted_amount DOUBLE,
    source_currency VARCHAR(3),
    target_currency VARCHAR(3),
    rate DOUBLE,
    transaction_date VARCHAR(200)
);