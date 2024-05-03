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
import java.util.List;
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
                                    System.out.print("Enter your gmail : ");
                                    String vemail = scanner.nextLine();
                                    System.out.print("Enter your password :");
                                    String vpassword = scanner.nextLine();
                                    if (service.validateUser(vemail, vpassword)) {
                                        System.out.println("Sucessfully Login");
                                        boolean exitJobSeekerOptions = false;
                                        while (!exitJobSeekerOptions) {
                                            System.out.println("Job Seeker Options:");
                                            System.out.println("1) Resume");
                                            System.out.println("2) Search job");
                                            System.out.println("3) status");
                                            System.out.println("4) Exit");
                                            int jobSeekerOption = scanner.nextInt();
                                            scanner.nextLine(); // Consume newline character
                                            switch (jobSeekerOption) {
                                                case 1:
                                                    // Logic for resume
                                                    boolean exitResumeOptions = false;
                                                    while (!exitResumeOptions) {
                                                        System.out.println("Resume Options:");
                                                        System.out.println("1) Create Resume");
                                                        System.out.println("2) Update Resume");
                                                        System.out.println("3) View Resume");
                                                        System.out.println("4) Delete Resume");
                                                        System.out.println("5) Exit");
                                                        int resumeOption = scanner.nextInt();
                                                        scanner.nextLine(); // Consume newline character

                                                        switch (resumeOption) {
                                                            case 1:
                                                                // Create Resume
                                                                boolean validloc1 = true;
                                                                String location = "";
                                                                while (validloc1) {
                                                                    System.out.println("Enter your location: ");
                                                                    location = scanner.nextLine();
                                                                    if (service.validString(location)) {
                                                                        validloc1 = false;
                                                                    } else {
                                                                        System.out.println("Invalid location");
                                                                    }
                                                                }

                                                                boolean validloc2 = true;
                                                                String skillset1 = "";
                                                                while (validloc2) {
                                                                    System.out.println("Enter your Skillset 1: ");
                                                                    skillset1 = scanner.nextLine();
                                                                    if (service.validString(skillset1)) {
                                                                        validloc2 = false;
                                                                    } else {
                                                                        System.out.println("Invalid skillse1");
                                                                    }
                                                                }

                                                                boolean validloc3 = true;
                                                                String skillset2 = "";
                                                                while (validloc3) {
                                                                    System.out.println("Enter your Skillset 2: ");
                                                                    skillset2 = scanner.nextLine();
                                                                    if (service.validString(skillset2)) {
                                                                        validloc3 = false;
                                                                    } else {
                                                                        System.out.println("Invalid skillse2");
                                                                    }
                                                                }

                                                                boolean validloc4 = true;
                                                                String skillset3 = "";
                                                                while (validloc4) {
                                                                    System.out.println("Enter your Skillset 3: ");
                                                                    skillset3 = scanner.nextLine();
                                                                    if (service.validString(skillset3)) {
                                                                        validloc4 = false;
                                                                    } else {
                                                                        System.out.println("Invalid skillse3");
                                                                    }
                                                                }
                                                                Resume resume = new Resume(location, skillset1, skillset2, skillset3);
                                                                service.addResume(resume, vemail);
                                                                System.out.println("Resume created successfully!");
                                                                break;
                                                            case 2:
                                                                scanner.nextLine();
                                                                // Update Resume
                                                                boolean validuploc = true;
                                                                String newLocation = "";
                                                                while (validuploc) {
                                                                    System.out.println("Enter your location: ");
                                                                    newLocation = scanner.nextLine();
                                                                    if (service.validString(newLocation)) {
                                                                        validuploc = false;
                                                                    } else {
                                                                        System.out.println("Invalid location");
                                                                    }
                                                                }

                                                                boolean validupskill1 = true;
                                                                String newSkillset1 = "";
                                                                while (validupskill1) {
                                                                    System.out.println("Enter the new Skillset 1:");
                                                                    newSkillset1 = scanner.nextLine();
                                                                    if (service.validString(newSkillset1)) {
                                                                        validupskill1 = false;
                                                                    } else {
                                                                        System.out.println("Invalid skillset1");
                                                                    }
                                                                }

                                                                boolean validupskill2 = true;
                                                                String newSkillset2 = "";
                                                                while (validupskill2) {
                                                                    System.out.println("Enter the new Skillset 2:");
                                                                    newSkillset2 = scanner.nextLine();
                                                                    if (service.validString(newSkillset2)) {
                                                                        validupskill2 = false;
                                                                    } else {
                                                                        System.out.println("Invalid skillset2");
                                                                    }
                                                                }

                                                                boolean validupskill3 = true;
                                                                String newSkillset3 = "";
                                                                while (validupskill3) {
                                                                    System.out.println("Enter the new Skillset 3:");
                                                                    newSkillset3 = scanner.nextLine();
                                                                    if (service.validString(newSkillset3)) {
                                                                        validupskill3 = false;
                                                                    } else {
                                                                        System.out.println("Invalid skillset3");
                                                                    }
                                                                }

                                                                Resume updatedResume = new Resume(newLocation, newSkillset1, newSkillset2, newSkillset3);
                                                                service.updateResume(updatedResume, vemail);
                                                                System.out.println("Resume updated successfully!");

                                                                break;
                                                            case 3:
                                                                scanner.nextLine();
                                                                // Delete Resume
                                                                service.deleteResume(vemail);
                                                                System.out.println("Resume deleted successfully!");
                                                                break;

                                                            case 4:
                                                                scanner.nextLine();
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
                                                                exitResumeOptions = true;
                                                                break;
                                                            default:
                                                                System.out.println("Invalid option");
                                                        }
                                                    }
                                                    break;
                                                case 2:
                                                    // Search job
                                                    boolean exitSearchJobOptions = false;
                                                    while (!exitSearchJobOptions) {
                                                        System.out.println("Search Job Options:");
                                                        System.out.println("1) View All Job Postings");
                                                        System.out.println("2) Filter Job Postings");
                                                        System.out.println("3) Exit");
                                                        int searchOption = scanner.nextInt();
                                                        scanner.nextLine(); // Consume newline character

                                                        switch (searchOption) {
                                                            case 1:
                                                                // View All Job Postings
                                                                System.out.println("All Job Postings:");
                                                                // Retrieve all job postings from the database
                                                                List<Jobposting> allJobPostings = service.getAllJobPostings();
                                                                for (Jobposting posting : allJobPostings) {
                                                                    System.out.println(posting);
                                                                }
                                                                break;
                                                            case 2:
                                                                // Filter Job Postings
                                                                System.out.println("Filter Job Postings:");
                                                                System.out.println("Enter filter criteria (e.g., companyname,location,jobskill):");
                                                                String filterCriteria = scanner.nextLine();
                                                                // Retrieve filtered job postings from the database based on filter criteria
                                                                List<Jobposting> filteredJobPostings = service.getFilteredJobPostings(filterCriteria);
                                                                if (filteredJobPostings.isEmpty()) {
                                                                    System.out.println("No job postings found matching the filter criteria.");
                                                                } else {
                                                                    System.out.println("Filtered Job Postings:");
                                                                    for (Jobposting posting : filteredJobPostings) {
                                                                        System.out.println(posting);
                                                                    }
                                                                }
                                                                break;
                                                            case 3:
                                                                exitSearchJobOptions = true;
                                                                break;
                                                            default:
                                                                System.out.println("Invalid option");
                                                        }
                                                    }
                                                    break;
                                                case 3:

                                                case 4:
                                                    exitJobSeekerOptions = true;
                                                    break;
                                                default:
                                                    System.out.println("Invalid option");
                                            }
                                        }
                                        break;
                                    }
                                case 3:
                                    goback1 = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice");

                            }
                            break;
                        }
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
                                    System.out.println("Employee added successfully!");
                                    break;
                                case 2:
                                        scanner.nextLine();
                                        System.out.print("Enter your gmail : ");
                                        String empemail = scanner.nextLine();
                                        System.out.print("Enter your password :");
                                        String emppassword = scanner.nextLine();
                                        if (service.validateUser(empemail, emppassword)) {
                                            System.out.println("Sucessfully Login");
                                            boolean exitEmployeeCrud = true;
                                            while (exitEmployeeCrud) {
                                                System.out.println("***********");
                                                System.out.println("1) Job Postings");
                                                System.out.println("2) which users applied");
                                                System.out.println("3) Exit");
                                            int option = scanner.nextInt();
                                            scanner.nextLine();
                                            switch (option) {
                                                case 1:
                                                    boolean validname = true;
                                                    String Compname="";
                                                    while(validname) {
                                                        System.out.println("Enter the new Compname:");
                                                        Compname = scanner.nextLine();
                                                        if (service.validString(Compname)) {
                                                            validname = false;
                                                        } else {
                                                            System.out.println("Invalid Compname");
                                                        }
                                                    }

                                                    boolean validloc = true;
                                                    String Comploc="";
                                                    while(validloc) {
                                                        System.out.println("Enter the new Comploc:");
                                                        Comploc = scanner.nextLine();
                                                        if (service.validString(Comploc)) {
                                                            validloc = false;
                                                        } else {
                                                            System.out.println("Invalid Comploc");
                                                        }
                                                    }

                                                    boolean validlskill = true;
                                                    String Jobskill="";
                                                    while(validlskill) {
                                                        System.out.println("Enter the new Jobskill:");
                                                        Jobskill = scanner.nextLine();
                                                        if (service.validString(Jobskill)) {
                                                            validlskill = false;
                                                        } else {
                                                            System.out.println("Invalid Jobskill");
                                                        }
                                                    }

                                                    boolean validexp = true;
                                                    int Expneed=0;
                                                    while(validexp) {
                                                        System.out.println("Enter the experience needed:");
                                                        Expneed = scanner.nextInt();
                                                        if (service.validInt(Expneed)) {
                                                            validexp = false;
                                                        } else {
                                                            System.out.println("Invalid experience needed");
                                                        }
                                                    }
                                                        // Create job posting object
                                                    Jobposting jobposting = new Jobposting(Compname, Comploc, Jobskill, Expneed);
                                                    service.jobDetails(jobposting);
                                                    System.out.println("Job posting created successfully!");
                                                    break;
                                                case 2:

                                                case 3:
                                                    exitEmployeeCrud = false;
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
