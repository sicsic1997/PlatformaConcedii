--== CREATES SCHEMA
CREATE SCHEMA 'PLATFORMA_CONCEDII';

--== CREATES USERS TABLE
USE PLATFORMA_CONCEDII;
CREATE TABLE USERS (

	ID INT PRIMARY KEY,
    USER_NAME VARCHAR(45),
    PASSWORD VARCHAR(45),
    FIRST_NAME VARCHAR(45),
    LAST_NAME VARCHAR(45),
    MANAGER_ID INT

);