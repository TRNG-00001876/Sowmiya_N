use model;

-- Create Employee table
CREATE TABLE Employee (
    Empcode INT PRIMARY KEY,
    EmpFname VARCHAR(20),
    EmpLname VARCHAR(20),
    Job VARCHAR(40),
    Manager VARCHAR(20),
    HireDate DATE,
    Salary INT,
    Commission INT,
    DeptCode INT,
    FOREIGN KEY (DeptCode) REFERENCES Department(Deptcode)
);

-- Create Department table
CREATE TABLE Department (
    Deptcode INT,
    DeptName VARCHAR(30),
    Location VARCHAR(40),
    PRIMARY KEY (Deptcode)
);

 

-- Alter Department table by adding unique constraint to DeptName
ALTER TABLE Department
ADD CONSTRAINT UK_DeptName UNIQUE (DeptName);

-- Alter Department table by adding constraint not null for Location
ALTER TABLE Department
ALTER COLUMN Location VARCHAR(40) NOT NULL;

-- Alter Employee table by adding foreign key constraint to Deptcode
ALTER TABLE Employee
ADD CONSTRAINT FK_DeptCode FOREIGN KEY (DeptCode) REFERENCES Department(Deptcode);


select * from Department
select * from Employee



INSERT INTO DEPARTMENT VALUES (10, 'FINANCE', 'EDINBURGH'),
                              (20,'SOFTWARE','PADDINGTON'),
                              (30, 'SALES', 'MAIDSTONE'),
                              (40,'MARKETING', 'DARLINGTON'),
                              (50,'ADMIN', 'BIRMINGHAM');
                       
INSERT INTO EMPLOYEE  
VALUES (9369, 'TONY', 'STARK', 'SOFTWARE ENGINEER', 7902, '1980-12-17', 2800,0,20),
       (9499, 'TIM', 'ADOLF', 'SALESMAN', 7698, '1981-02-20', 1600, 300,30),    
       (9566, 'KIM', 'JARVIS', 'MANAGER', 7839, '1981-04-02', 3570,0,20),
       (9654, 'SAM', 'MILES', 'SALESMAN', 7698, '1981-09-28', 1250, 1400, 30),
       (9782, 'KEVIN', 'HILL', 'MANAGER', 7839, '1981-06-09', 2940,0,10),
       (9788, 'CONNIE', 'SMITH', 'ANALYST', 7566, '1982-12-09', 3000,0,20),
       (9839, 'ALFRED', 'KINSLEY', 'PRESIDENT', 7566, '1981-11-17', 5000,0, 10),
       (9844, 'PAUL', 'TIMOTHY', 'SALESMAN', 7698, '1981-09-08', 1500,0,30),
       (9876, 'JOHN', 'ASGHAR', 'SOFTWARE ENGINEER', 7788, '1983-01-12',3100,0,20),
       (9900, 'ROSE', 'SUMMERS', 'TECHNICAL LEAD', 7698, '1981-12-03', 2950,0, 20),
       (9902, 'ANDREW', 'FAULKNER', 'ANAYLYST', 7566, '1981-12-03', 3000,0, 10),
       (9934, 'KAREN', 'MATTHEWS', 'SOFTWARE ENGINEER', 7782, '1982-01-23', 3300,0,20),
       (9591, 'WENDY', 'SHAWN', 'SALESMAN', 7698, '1981-02-22', 500,0,30),
       (9698, 'BELLA', 'SWAN', 'MANAGER', 7839, '1981-05-01', 3420, 0,30),
       (9777, 'MADII', 'HIMBURY', 'ANALYST', 7839, '1981-05-01', 2000, 200, NULL),
       (9860, 'ATHENA', 'WILSON', 'ANALYST', 7839, '1992-06-21', 7000, 100, 50),
       (9861, 'JENNIFER', 'HUETTE', 'ANALYST', 7839, '1996-07-01', 5000, 100, 50); 


--queries
--1
SELECT 
    e.EmpFname AS EMPFNAME,
    e.EmpLname AS EMPLNAME,
    e.DeptCode,
    d.DeptName,
    d.Location
FROM 
    Employee e
JOIN 
    Department d ON e.DeptCode = d.Deptcode
ORDER BY 
    e.EmpFname ASC,
    d.Location ASC;
--2
SELECT 
    EmpFname AS EMPFNAME,
    SUM(Salary) AS "TOTAL SALARY"
FROM 
    Employee
GROUP BY 
    EmpFname;
--3
SELECT 
    MAX(Salary) AS "MAX SALARY",
    (SELECT MAX(Salary) 
     FROM Employee 
     WHERE Salary < (SELECT MAX(Salary) FROM Employee)) AS "2nd MAX SALARY"
FROM 
    Employee;
--4
SELECT 
    SUM(Salary) AS "TOTAL SALARY"
FROM 
    Employee
WHERE 
    Job = 'ANALYST' 
    AND DeptCode = 20;
--5
SELECT 
    AVG(Salary) AS "Average Salary",
    MIN(Salary) AS "Minimum Salary",
    MAX(Salary) AS "Maximum Salary"
FROM 
    Employee
WHERE 
    Job = 'ANALYST';
--6
SELECT 
    Deptcode,
    DeptName
FROM 
    Department
WHERE 
    Location = 'EDINBURGH';
--7
SELECT 
    e.*
FROM 
    Employee e
JOIN 
    Department d ON e.DeptCode = d.Deptcode
WHERE 
    d.DeptName = 'FINANCE';
--8
SELECT 
    DeptCode,
    AVG(Salary) AS "Average Salary"
FROM 
    Employee
GROUP BY 
    DeptCode;

--9
SELECT TOP 10
    *
FROM 
    Employee
ORDER BY 
    Salary DESC;

--10
SELECT 
    *
FROM 
    Employee
WHERE 
    HireDate <= DATEADD(YEAR, -1, GETDATE())

--11
SELECT 
    *
FROM 
    Employee
WHERE 
    Salary > (SELECT AVG(Salary) FROM Employee);

--12
SELECT 
    *
FROM 
    Employee
WHERE 
    DATEDIFF(YEAR, HireDate, GETDATE()) > 5;

--13
SELECT 
    d.Deptcode,
    d.DeptName,
    d.Location,
    COUNT(e.Empcode) AS EmployeeCount
FROM 
    Department d
LEFT JOIN 
    Employee e ON d.Deptcode = e.DeptCode
GROUP BY 
    d.Deptcode, d.DeptName, d.Location
ORDER BY 
    EmployeeCount DESC;

--14
SELECT 
    Job,
    COUNT(*) AS EmployeeCount
FROM 
    Employee
GROUP BY 
    Job
ORDER BY 
    EmployeeCount DESC;

--15
SELECT 
    Manager,
    COUNT(*) AS EmployeeCount
FROM 
    Employee
GROUP BY 
    Manager
ORDER BY 
    EmployeeCount DESC;
