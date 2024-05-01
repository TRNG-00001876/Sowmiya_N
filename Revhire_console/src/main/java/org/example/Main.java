package org.example;

import org.example.dao.Dao;
import org.example.dao.DaoImpl;
import org.example.model.Useregister;
import org.example.model.Employeereg;
import org.example.model.Jobposting;
import org.example.service.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=revhire_console;integratedSecurity=True;encrypt=True;TrustServerCertificate=True");
            Dao Dao = DaoImpl.getInstance(connection);
            Service Service = new Service(Dao);
            Scanner scanner = new Scanner(System.in);
            boolean exit=true;
            while(exit) {
                System.out.println("JOB PORTAL");
                System.out.println("Enter your choice: ");
                System.out.println("1. Job Seeker");
                System.out.println("2. Employee");
                System.out.println("3. Exit");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        boolean goback1 = true;
                        while (goback1) {
                            System.out.println("***********");
                            System.out.println("1)Jobseeker register");
                            System.out.println("2)Jobseeker login");
                            System.out.println("3)Exit");
                            int job_seeker = scanner.nextInt();
                            switch (job_seeker) {
                                case 1:
                                    System.out.print("Enter your name: ");
                                    String Username = scanner.nextLine();
                                    if (Service.isValidusername(Username)) {
                                        System.out.println("Username valid");
                                    } else {
                                        System.out.println("Username not valid");
                                        break;
                                    }
                                    System.out.print("Enter Password: ");
                                    String Password = scanner.nextLine();
                                    if (Service.isValidpassword(Password)) {
                                        System.out.println("Password is valid.");
                                    } else {
                                        System.out.println("Password is not valid. Password must contain at least 1 capital letter, 1 special character, 1 number, and be at least 8 characters long.");
                                        break;
                                    }
                                    System.out.print("Enter your EmailId: ");
                                    String Email = scanner.nextLine();
                                    if (Service.isValidemail(Email)) {
                                        System.out.println("Email is valid.");
                                    } else {
                                        System.out.println("Invalid email address format. Please enter a valid email address.");
                                        break;
                                    }
                                    Useregister useregister = new Useregister(Username, Password, Email);
                                    Service.userDetails(useregister);
                                    System.out.println("Jobseeker added successfully!");
                                    break;

                                case 2:
                                    System.out.println("Enter your gmail : ");
                                    scanner.nextLine();
                                    String vemail = scanner.nextLine();
                                    System.out.println("Enter your password :");
                                    String vpassword = scanner.nextLine();
                                    Service.validateUser(vemail, vpassword);
                                    System.out.println("Login is successfull");
                                    break;

                                case 3:
                                    goback1 = false;
                            }
                        }
                        break;

                    case 2 : boolean goback2 = true;
                        while (goback2) {
                            System.out.println("***********");
                            System.out.println("1)register");
                            System.out.println("2)login");
                            System.out.println("3)Job posting");
                            System.out.println("4)Exit");
                            int Employee = scanner.nextInt();
                            switch (Employee) {
                                case 1:
                                    boolean validName=false;
                                    String empname;
                                    do {
                                        System.out.print("Enter your name: ");
                                        empname = scanner.nextLine();
                                        if (Service.isValidusername(empname)) {
                                            validName = true;
                                        } else {
                                            System.out.println("Username not valid");
                                        }
                                    }while(!validName);

                                    boolean validPassword=false;
                                    String emppassword;
                                    do {
                                        System.out.print("Enter Password: ");
                                        emppassword = scanner.nextLine();
                                        // Validate password
                                        if (Service.isValidpassword(emppassword)) {
                                            validPassword = true;
                                        } else {
                                            System.out.println("Password is not valid. Password must contain at least 1 capital letter, 1 special character, 1 number, and be at least 8 characters long.");
                                        }
                                    }while(!validPassword);

                                    boolean validEmail=false;
                                    String empemail;
                                    do {
                                        System.out.print("Enter your EmailId: ");
                                        empemail = scanner.nextLine();
                                        //validate email
                                        if (Service.isValidemail(empemail))
                                        {
                                            validEmail=true;
                                        }
                                        else
                                        {
                                            System.out.println("Invalid email address format. Please enter a valid email address.--+");
                                        }
                                    }while(!validEmail);

                                    Employeereg employeereg = new Employeereg(empname, emppassword, empemail);
                                    Service.employeeDetails(employeereg);
                                    break;

                                case 2:
                                    System.out.println("Enter your gmail : ");
                                    scanner.nextLine();
                                    String vempemail = scanner.nextLine();
                                    System.out.println("Enter your password :");
                                    String vemppassword = scanner.nextLine();
                                    Service.validateemployee(vempemail, vemppassword);
                                    System.out.println("Login is successfull");
                                    break;

                                case 3:
                                    System.out.println("Enter the company name");
                                    String Compname = scanner.nextLine();
                                    scanner.nextLine();
                                    System.out.println("Enter the company location");
                                    String Comploc = scanner.nextLine();
                                    System.out.println("Enter the jobskill requirement");
                                    String Jobskill = scanner.nextLine();
                                    System.out.println("Enter the experience needed");
                                    int Expneed = scanner.nextInt();
                                    scanner.nextLine();
                                    // Create employee object
                                    Jobposting jobposting = new Jobposting(Compname, Comploc, Jobskill, Expneed);
                                    // Add job seeker
                                    Service.jobDetails(jobposting);
                                    System.out.println("Employee added successfully!");
                                    break;

                                case 4:
                                    goback2 = false;
                                    break;
                            }
                        }
                        break;
                    case 3: exit=false;
                            break;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}