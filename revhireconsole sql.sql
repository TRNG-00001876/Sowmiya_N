create database rev_con;
use rev_con;

CREATE TABLE Users (
    userid INT IDENTITY(1,1) PRIMARY KEY,
    email VARCHAR(50) UNIQUE NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE Employees (
    empid INT IDENTITY(1,1) PRIMARY KEY,
    empemail VARCHAR(50) UNIQUE NOT NULL,
    empname VARCHAR(50) NOT NULL,
    emppassword VARCHAR(50) NOT NULL
);

CREATE TABLE Resumes (
    resumeId INT IDENTITY(1,1) PRIMARY KEY,
    location VARCHAR(100),
    skillset1 VARCHAR(50),
    skillset2 VARCHAR(50),
    skillset3 VARCHAR(50),
    userEmail VARCHAR(50),
    FOREIGN KEY (userEmail) REFERENCES Users(email)
);

CREATE TABLE Jobposting (
    jobid INT IDENTITY(1,1) PRIMARY KEY,
    Compname VARCHAR(100),
    Comploc VARCHAR(100),
    Jobskill VARCHAR(100),
    Expneed INT
);

select * from users
select * from employees
select * from resumes
select * from jobposting