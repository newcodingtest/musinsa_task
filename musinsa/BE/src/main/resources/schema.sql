CREATE TABLE IF NOT EXISTS Accessory (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       brand VARCHAR(100) NOT NULL,
                       price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Bag (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     brand VARCHAR(100) NOT NULL,
                                     price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Bottom (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     brand VARCHAR(100) NOT NULL,
                                     price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Hat (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     brand VARCHAR(100) NOT NULL,
                                     price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Outer (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     brand VARCHAR(100) NOT NULL,
                                     price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Sneakers (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     brand VARCHAR(100) NOT NULL,
                                     price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Socks (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     brand VARCHAR(100) NOT NULL,
                                     price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Top (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     brand VARCHAR(100) NOT NULL,
                                     price DECIMAL(10, 2) NOT NULL
);