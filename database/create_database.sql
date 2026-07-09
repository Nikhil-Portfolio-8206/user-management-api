/*
=========================================================
 Project : User Management REST API
 Database: MySQL
 Author  : Nikhil
 Purpose : Create database for the project
=========================================================
*/

-- Drop the database if it already exists
-- Uncomment this only if you want a fresh database

-- DROP DATABASE IF EXISTS user_management_db;

-- Create the database

CREATE DATABASE IF NOT EXISTS user_management_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- Verify the database exists

SHOW DATABASES;

-- Select the database

USE user_management_db;

-- Display the currently selected database

SELECT DATABASE();