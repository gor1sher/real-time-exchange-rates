CREATE TABLE IF NOT EXISTS currency(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    priceUSD DOUBLE,
    date_of_the_exchange_rate DATE
);

CREATE TABLE IF NOT EXISTS expenses(
    id INT PRIMARY KEY AUTO_INCREMENT,
    client_id INTEGER,
    expense DOUBLE,
    expense_limit DOUBLE,
    limit_exceeded BOOLEAN,
    localDate DATE
);