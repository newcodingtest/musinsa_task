CREATE TABLE IF NOT EXISTS Product (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       category VARCHAR(100) NOT NULL,
                       brand VARCHAR(100) NOT NULL,
                       price DECIMAL(10, 2) NOT NULL
);

