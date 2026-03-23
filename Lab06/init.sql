-- init.sql
CREATE DATABASE labdb;

\c labdb

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT
);

INSERT INTO users (name, email) VALUES
('Alice','alice@gmail.com'),
('Bob','bob@gmail.com'),
('Charlie','charlie@gmail.com');