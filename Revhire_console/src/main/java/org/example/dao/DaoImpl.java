package org.example.dao;

import org.example.model.Employeereg;
import org.example.model.Useregister;
import org.example.model.Resume;
import org.example.model.Jobposting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements Dao {
    private static DaoImpl instance;
    private Connection connection;


    // Private constructor to prevent external instantiation
    private DaoImpl(Connection connection) {
        this.connection = connection;
    }

    // Method to get the singleton instance
    public static synchronized DaoImpl getInstance(Connection connection) {
        if (instance == null) {
            instance = new DaoImpl(connection);
        }
        return instance;
    }

    @Override
    public void userDetails(Useregister useregister) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  users(Username,Email,Password) VALUES (?, ?, ?)");
            preparedStatement.setString(1, useregister.getUsername());
            preparedStatement.setString(2, useregister.getEmail());
            preparedStatement.setString(3, useregister.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jobDetails(Jobposting jobposting) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  jobposting(Compname,Comploc,Jobskill,Expneed) VALUES (?,?, ?, ?)");
            preparedStatement.setString(1, jobposting.getCompname());
            preparedStatement.setString(2, jobposting.getComploc());
            preparedStatement.setString(3, jobposting.getJobskill());
            preparedStatement.setInt(4, jobposting.getExpneed());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void employeeDetails(Employeereg employeereg) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Employees(empname,empemail,emppassword) VALUES (?,?, ?)");
            preparedStatement.setString(1, employeereg.getEmpname());
            preparedStatement.setString(2, employeereg.getEmpemail());
            preparedStatement.setString(3, employeereg.getEmppassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validateUser(String vemail, String vpassword) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE Email=? AND Password=?");
            preparedStatement.setString(1, vemail);
            preparedStatement.setString(2, vpassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String one = resultSet.getString("vemail");
                String two = resultSet.getString("vpassword");
                if ((vemail == one) && (vpassword == two)) {
                    return true;
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean isValidpassword(String validpassword) {
        // Define the regular expression pattern for password validation
        String pattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        // Check if the provided password matches the pattern
        return validpassword.matches(pattern);
    }


    @Override
    public boolean isValidemail(String validemail) {
        String emailpattern = "^[a-zA-Z0-9._%+-]+@gmail.com$";
        return validemail.matches(emailpattern);
    }

    @Override
    public boolean isValidusername(String validusername) {
        String usernamepattern = "^[A-Za-z]{2,19}$";
        return validusername.matches(usernamepattern);
    }


    @Override
    public boolean validateemployee(String vempemail, String vemppassword) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE emppassword=? AND empemail=?");
            preparedStatement.setString(1, vempemail);
            preparedStatement.setString(2, vemppassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String one = resultSet.getString("vempemail");
                String two = resultSet.getString("vemppassword");
                if ((vempemail == one) && (vemppassword == two)) {
                    return true;
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //resume

    @Override
    public boolean addResume(Resume resume, String userEmail) {
        String query = "INSERT INTO resumes (location, skillset1, skillset2, skillset3, userEmail) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, resume.getLocation());
            preparedStatement.setString(2, resume.getSkillset1());
            preparedStatement.setString(3, resume.getSkillset2());
            preparedStatement.setString(4, resume.getSkillset3());
            preparedStatement.setString(5, userEmail);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateResume(Resume resume, String userEmail) {
        String query = "UPDATE resumes SET location=?, skillset1=?, skillset2=?, skillset3=? WHERE userEmail=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, resume.getLocation());
            preparedStatement.setString(2, resume.getSkillset1());
            preparedStatement.setString(3, resume.getSkillset2());
            preparedStatement.setString(4, resume.getSkillset3());
            preparedStatement.setString(5, userEmail);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteResume(String userEmail) {
        String query = "DELETE FROM resumes WHERE userEmail=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userEmail);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Resume getResume(String userEmail) {
        String query = "SELECT * FROM resumes WHERE userEmail=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Resume(
                        resultSet.getString("location"),
                        resultSet.getString("skillset1"),
                        resultSet.getString("skillset2"),
                        resultSet.getString("skillset3")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //employee crud

    @Override
    public boolean addEmployee(Employeereg employee) {
        String query = "INSERT INTO Employees (empname, empemail, emppassword) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employee.getEmpname());
            preparedStatement.setString(2, employee.getEmpemail());
            preparedStatement.setString(3, employee.getEmppassword());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employeereg employee) {
        String query = "UPDATE Employees SET empname=?, emppassword=? WHERE empemail=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employee.getEmpname());
            preparedStatement.setString(2, employee.getEmppassword());
            preparedStatement.setString(3, employee.getEmpemail());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEmployee(String email) {
        String query = "DELETE FROM Employees WHERE empemail=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

