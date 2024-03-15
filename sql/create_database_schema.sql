
CREATE SCHEMA IF NOT EXISTS user_management AUTHORIZATION core_api_user;

CREATE TABLE IF NOT EXISTS user_management.users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(100),
    email VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(10),
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_management.addresses (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    first_line VARCHAR(255) NOT NULL,
    second_line VARCHAR(255),
    street VARCHAR(255) NOT NULL,
    landmark VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(50) NOT NULL,
    postal_code VARCHAR(10) NOT NULL,
    country VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES user_management.users(id)
);
