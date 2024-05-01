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
            while (true) {
                // Prompt user for operation
                System.out.println("JOB PORTAL");
                System.out.println("Enter your choice: ");
                System.out.println("1. Job Seeker");
                System.out.println("2. Employee");
                System.out.println("3. Exit");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1: System.out.println("***********");
                            System.out.println("1)Jobseeker register");
                            System.out.println("2)Jobseeker login");
                            //System.out.println("3)Forgot password");
                            System.out.println("3)Exit");
                            int job_seeker=scanner.nextInt();
                            switch (job_seeker)
                            {
                                case 1:System.out.println("Enter the id");
                                    int Userid=scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter your name: ");
                                    String Username = scanner.nextLine();
                                    System.out.print("Enter Password: ");
                                    String Password = scanner.nextLine();
                                    if (Dao.isValidpassword(Password)) {
                                        System.out.println("Password is valid.");
                                    } else {
                                        System.out.println("Password is not valid. Password must contain at least 1 capital letter, 1 special character, 1 number, and be at least 8 characters long.");
                                        break;
                                    }
                                    System.out.print("Enter your EmailId: ");
                                    String Email = scanner.nextLine();
                                    if (Dao.isValidemail(Email)) {
                                        System.out.println("Email is valid.");
                                    } else {
                                        System.out.println("Invalid email address format. Please enter a valid email address.");
                                        break;
                                    }
                                    Useregister useregister = new Useregister(Userid,Username, Password, Email);
                                    Service.userDetails(useregister) ;
                                    System.out.println("Jobseeker added successfully!");
                                    break;
                                case 2 :
                                    System.out.println("Enter your gmail : ");
                                    scanner.nextLine();
                                    String vemail=scanner.nextLine();
                                    System.out.println("Enter your password :");
                                    String vpassword=scanner.nextLine();
                                    Service.validateUser(vemail,vpassword);
                                    System.out.println("Login is successfull");
                                    break;
                                case 3:
                                    // Exit the program
                                    System.out.println("Exiting program...");
                                    System.exit(0);
                            }
                        // Prompt user for job seeker details
                    break;
                    case 2:
                        System.out.println("***********");
                        System.out.println("1)register");
                        System.out.println("2)login");
                        System.out.println("3)Job posting");
                        System.out.println("4)Exit");
                        int Employee=scanner.nextInt();
                        switch (Employee) {
                            case 1:
                                System.out.println("Enter your name: ");
                                String empname = scanner.nextLine();
                                scanner.nextLine();
                                System.out.println("Enter Password: ");
                                String emppassword = scanner.nextLine();
                                // Validate password
                                if (Dao.isValidpassword(emppassword)) {
                                    System.out.println("Password is valid.");
                                } else {
                                    System.out.println("Password is not valid. Password must contain at least 1 capital letter, 1 special character, 1 number, and be at least 8 characters long.");
                                    break;
                                }
                                System.out.println("Enter your EmailId: ");
                                String empemail = scanner.nextLine();
                                //validate email
                                if (Dao.isValidemail(empemail)) {
                                    System.out.println("Email is valid.");
                                } else {
                                    System.out.println("Invalid email address format. Please enter a valid email address.--+");
                                    break;
                                }
                                System.out.println("Enter your mobile number ");
                                String empphno = scanner.nextLine();
                                Employeereg employeereg = new Employeereg(empname, emppassword, empemail, empphno);
                                Service.employeeDetails(employeereg);
                                System.out.println("Employee added successfully!");
                                break;
                            case 2 :
                                System.out.println("Enter your gmail : ");
                                scanner.nextLine();
                                String vempemail=scanner.nextLine();
                                System.out.println("Enter your password :");
                                String vemppassword=scanner.nextLine();
                                Service.validateemployee(vempemail,vemppassword);
                                System.out.println("Login is successfull");
                                break;
                            case 3:
                                System.out.println("Enter the employee id");
                                int Empid = scanner.nextInt();
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
                                Jobposting jobposting = new Jobposting(Empid, Compname, Comploc, Jobskill, Expneed);
                                // Add job seeker
                                Service.jobDetails(jobposting);
                                System.out.println("Employee added successfully!");
                                break;
                            case 4:// Exit the program
                                System.out.println("Exiting program...");
                                System.exit(0);
                            default:
                                System.out.println("Invalid choice!");
                        }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}