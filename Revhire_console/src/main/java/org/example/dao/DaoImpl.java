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
                String one = resultSet.getString("Email");
                String two = resultSet.getString("Password");
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
                String one = resultSet.getString("empemail");
                String two = resultSet.getString("emppassword");
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

    //validate string,int for jobposting
    @Override
    public boolean validString(String details)
    {
        if(details==null ||details.isEmpty())
        {
            return  false;
        }
        for(char c:details.toCharArray())
        {
            if(! Character.isLetter(c) || c==' ' || c=='/' || c==',' || c=='-')
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean validInt(int detail){
        String detailPattern = "^(?:100|[1-9]?[0-9])$";
        String detailString = Integer.toString(detail); // Convert int to String
        return detailString.matches(detailPattern);
    }

    //search job
    @Override
    public List<Jobposting> getAllJobPostings() {
        List<Jobposting> jobPostings = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Jobposting");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Jobposting jobPosting = new Jobposting(
                        resultSet.getString("Compname"),
                        resultSet.getString("Comploc"),
                        resultSet.getString("Jobskill"),
                        resultSet.getInt("Expneed")
                );
                jobPostings.add(jobPosting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobPostings;
    }

    @Override
    public List<Jobposting> getFilteredJobPostings(String filterCriteria) {
        List<Jobposting> filteredJobPostings = new ArrayList<>();
        String query = "SELECT * FROM Jobposting WHERE Compname LIKE ? OR Comploc LIKE ? OR Jobskill LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + filterCriteria + "%");
            preparedStatement.setString(2, "%" + filterCriteria + "%");
            preparedStatement.setString(3, "%" + filterCriteria + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String compname = resultSet.getString("Compname");
                String comploc = resultSet.getString("Comploc");
                String jobskill = resultSet.getString("Jobskill");
                int expneed = resultSet.getInt("Expneed");

                // Create a Jobposting object and add it to the list
                Jobposting jobPosting = new Jobposting(compname, comploc, jobskill, expneed);
                filteredJobPostings.add(jobPosting);
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions here
            e.printStackTrace();
        }
        return filteredJobPostings;
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

}

