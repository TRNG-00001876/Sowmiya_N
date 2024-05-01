package org.example;
import org.example.dao.Dao;
import org.example.dao.DaoImpl;
import org.example.model.Useregister;
import org.example.model.Employeereg;
import org.example.model.Resume;
import org.example.model.Jobposting;
import org.example.service.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=rev_con;integratedSecurity=True;encrypt=True;TrustServerCertificate=True");
            Dao dao = DaoImpl.getInstance(connection);
            Service service = new Service(dao);
            Scanner scanner = new Scanner(System.in);
            boolean exit = true;
            while (exit) {
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
                                    scanner.nextLine();
                                    boolean validname = false;
                                    String Username;
                                    do {
                                        System.out.println("Enter your name: ");
                                        Username = scanner.nextLine();
                                        if (service.isValidusername(Username)) {
                                            validname = true;
                                        } else {
                                            System.out.println("Username not valid");
                                        }
                                    } while (!validname);

                                    boolean validemail = false;
                                    String Email;
                                    do {
                                        System.out.println("Enter your EmailId: ");
                                        Email = scanner.nextLine();
                                        if (service.isValidemail(Email)) {
                                            validemail = true;
                                        } else {
                                            System.out.println("Invalid email address format. Please enter a valid email address.");
                                        }
                                    } while (!validemail);

                                    boolean validpassword = false;
                                    String Password;
                                    do {
                                        System.out.println("Enter Password: ");
                                        Password = scanner.nextLine();
                                        if (service.isValidpassword(Password)) {
                                            validpassword = true;
                                        } else {
                                            System.out.println("Password is not valid. Password must contain at least 1 capital letter, 1 special character, 1 number, and be at least 8 characters long.");
                                        }
                                    } while (!validpassword);

                                    Useregister useregister = new Useregister(Username, Email, Password);
                                    service.userDetails(useregister);
                                    System.out.println("Jobseeker added successfully!");
                                    break;

                                case 2:
                                    scanner.nextLine();
                                    System.out.println("Enter your gmail : ");
                                    String vemail = scanner.nextLine();
                                    System.out.println("Enter your password :");
                                    String vpassword = scanner.nextLine();
                                    if (service.validateUser(vemail, vpassword)) {
                                        System.out.println("Sucessfully Login");
                                        boolean exitthis = true;
                                        while (exitthis) {
                                            System.out.println("1) Create Resume");
                                            System.out.println("2) Update Resume");
                                            System.out.println("3) Delete Resume");
                                            System.out.println("4) View Resume");
                                            System.out.println("5) Exit");
                                            int choicethis = scanner.nextInt();
                                            switch (choicethis) {
                                                case 1:
                                                    // Create Resume
                                                    System.out.println("Enter your location: ");
                                                    String location = scanner.nextLine();
                                                    System.out.println("Enter your Skillset 1: ");
                                                    String skillset1 = scanner.nextLine();
                                                    System.out.println("Enter your Skillset 2: ");
                                                    String skillset2 = scanner.nextLine();
                                                    System.out.println("Enter your Skillset 3: ");
                                                    String skillset3 = scanner.nextLine();
                                                    Resume resume = new Resume(location, skillset1, skillset2, skillset3);
                                                    service.addResume(resume, vemail);
                                                    System.out.println("Resume created successfully!");
                                                    break;
                                                case 2:
                                                    // Update Resume
                                                    System.out.println("Enter the new location: ");
                                                    String newLocation = scanner.nextLine();
                                                    System.out.println("Enter the new Skillset 1: ");
                                                    String newSkillset1 = scanner.nextLine();
                                                    System.out.println("Enter the new Skillset 2: ");
                                                    String newSkillset2 = scanner.nextLine();
                                                    System.out.println("Enter the new Skillset 3: ");
                                                    String newSkillset3 = scanner.nextLine();
                                                    Resume updatedResume = new Resume(newLocation, newSkillset1, newSkillset2, newSkillset3);
                                                    service.updateResume(updatedResume, vemail);
                                                    System.out.println("Resume updated successfully!");
                                                    break;
                                                case 3:
                                                    // Delete Resume
                                                    service.deleteResume(vemail);
                                                    System.out.println("Resume deleted successfully!");
                                                    break;
                                                case 4:
                                                    // View Resume
                                                    Resume viewedResume = service.getResume(vemail);
                                                    if (viewedResume != null) {
                                                        System.out.println("Resume details:");
                                                        System.out.println("Location: " + viewedResume.getLocation());
                                                        System.out.println("Skillset 1: " + viewedResume.getSkillset1());
                                                        System.out.println("Skillset 2: " + viewedResume.getSkillset2());
                                                        System.out.println("Skillset 3: " + viewedResume.getSkillset3());
                                                    } else {
                                                        System.out.println("Resume not found!");
                                                    }
                                                    break;
                                                case 5:
                                                    exitthis = false;
                                                    break;
                                                default:
                                                    System.out.println("Invalid choice");
                                            }
                                        }
                                    } else {
                                        System.out.println("Invalid email or password!");
                                    }
                                    break;
                                case 3:
                                    goback1 = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                            }
                        }
                        break;
                    case 2:
                        boolean goback2 = true;
                        while (goback2) {
                            System.out.println("***********");
                            System.out.println("1)Employee register");
                            System.out.println("2)Employee login");
                            System.out.println("3)Exit");
                            int Employee = scanner.nextInt();
                            switch (Employee) {
                                case 1:
                                    scanner.nextLine();
                                    boolean validName = false;
                                    String empname;
                                    do {
                                        System.out.print("Enter your name: ");
                                        empname = scanner.nextLine();
                                        if (service.isValidusername(empname)) {
                                            validName = true;
                                        } else {
                                            System.out.println("Username not valid");
                                        }
                                    } while (!validName);

                                    boolean validPassword = false;
                                    String emppassword;
                                    do {
                                        System.out.print("Enter Password: ");
                                        emppassword = scanner.nextLine();
                                        if (service.isValidpassword(emppassword)) {
                                            validPassword = true;
                                        } else {
                                            System.out.println("Password is not valid. Password must contain at least 1 capital letter, 1 special character, 1 number, and be at least 8 characters long.");
                                        }
                                    } while (!validPassword);

                                    boolean validEmail = false;
                                    String empemail;
                                    do {
                                        System.out.print("Enter your EmailId: ");
                                        empemail = scanner.nextLine();
                                        if (service.isValidemail(empemail)) {
                                            validEmail = true;
                                        } else {
                                            System.out.println("Invalid email address format. Please enter a valid email address.");
                                        }
                                    } while (!validEmail);

                                    Employeereg employeereg = new Employeereg(empname, emppassword, empemail);
                                    service.employeeDetails(employeereg);
                                    break;
                                case 2:
                                    boolean exitEmployeeCrud = true;
                                    while (exitEmployeeCrud) {
                                        System.out.println("***********");
                                        System.out.println("1) Employee CRUD");
                                        System.out.println("2) Job Postings");
                                        System.out.println("3) Exit");
                                        int option = scanner.nextInt();
                                        switch (option) {
                                            case 1:
                                                // Employee CRUD operations
                                                boolean exitEmployeeCrudCase = false;
                                                while (!exitEmployeeCrudCase) {
                                                    System.out.println("Employee CRUD Operations");
                                                    System.out.println("1) Add Employee");
                                                    System.out.println("2) Update Employee");
                                                    System.out.println("3) Delete Employee");
                                                    System.out.println("4) Exit");
                                                    int employeeChoice = scanner.nextInt();
                                                    scanner.nextLine(); // Consume newline character
                                                    switch (employeeChoice) {
                                                        case 1:
                                                            // Add Employee
                                                            scanner.nextLine(); // Consume newline character
                                                            System.out.println("Enter employee name: ");
                                                            String empName = scanner.nextLine();
                                                            System.out.println("Enter employee email: ");
                                                            String empEmail = scanner.nextLine();
                                                            System.out.println("Enter employee password: ");
                                                            String empPassword = scanner.nextLine();
                                                            Employeereg newEmployee = new Employeereg(empName, empPassword, empEmail);
                                                            service.addEmployee(newEmployee);
                                                            System.out.println("Employee added successfully!");
                                                            break;
                                                        case 2:
                                                            // Update Employee
                                                            scanner.nextLine(); // Consume newline character
                                                            System.out.println("Enter employee email to update: ");
                                                            String empEmailToUpdate = scanner.nextLine();
                                                            System.out.println("Enter new employee name: ");
                                                            String newEmpName = scanner.nextLine();
                                                            System.out.println("Enter new employee password: ");
                                                            String newEmpPassword = scanner.nextLine();
                                                            Employeereg updatedEmployee = new Employeereg(newEmpName, newEmpPassword, empEmailToUpdate);
                                                            service.updateEmployee(updatedEmployee);
                                                            System.out.println("Employee updated successfully!");
                                                            break;
                                                        case 3:
                                                            // Delete Employee
                                                            scanner.nextLine(); // Consume newline character
                                                            System.out.println("Enter employee email to delete: ");
                                                            String empEmailToDelete = scanner.nextLine();
                                                            service.deleteEmployee(empEmailToDelete);
                                                            System.out.println("Employee deleted successfully!");
                                                            break;
                                                        case 4:
                                                            // Exit Employee CRUD
                                                            exitEmployeeCrudCase = true;
                                                            break;
                                                        default:
                                                            System.out.println("Invalid choice");
                                                    }
                                                }
                                                break;
                                            case 2:
                                                // Job Posting
                                                scanner.nextLine();
                                                System.out.println("Enter your gmail : ");
                                                String vempemail = scanner.nextLine();
                                                System.out.println("Enter your password :");
                                                String vemppassword = scanner.nextLine();
                                                if (service.validateemployee(vempemail, vemppassword)) {
                                                    System.out.println("Login is successfull");
                                                    if (service.validateUser(vempemail, vemppassword)) {
                                                        System.out.println("Sucessfully Login");
                                                        boolean exitthis = true;
                                                        while (exitthis) {
                                                            System.out.println("1) Create Job Posting");
                                                            System.out.println("2) Exit");
                                                            int choicethis = scanner.nextInt();
                                                            switch (choicethis) {
                                                                case 1:
                                                                    scanner.nextLine();
                                                                    System.out.println("Enter the company name");
                                                                    String Compname = scanner.nextLine();
                                                                    System.out.println("Enter the company location");
                                                                    String Comploc = scanner.nextLine();
                                                                    System.out.println("Enter the job skill requirement");
                                                                    String Jobskill = scanner.nextLine();
                                                                    System.out.println("Enter the experience needed");
                                                                    int Expneed = scanner.nextInt();
                                                                    // Create job posting object
                                                                    Jobposting jobposting = new Jobposting(Compname, Comploc, Jobskill, Expneed);
                                                                    // Add job posting
                                                                    service.jobDetails(jobposting);
                                                                    System.out.println("Job posting created successfully!");
                                                                    break;
                                                                case 2:
                                                                    exitthis = false;
                                                                    break;
                                                                default:
                                                                    System.out.println("Invalid choice");
                                                            }
                                                        }
                                                    }
                                                }
                                                break;
                                            case 3:
                                                goback2 = false;
                                                break;
                                            default:
                                                System.out.println("Invalid choice");
                                        }
                                    }
                                    break;

                                case 3:
                                    goback2 = false;
                                    break;
                            }
                        }
                        break;
                    case 3:
                        exit = false;
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

