package org.example.dao;

import org.example.model.Employeereg;
import org.example.model.Useregister;
import org.example.model.Jobposting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  jobSeeker(UserId,Username,Password,Email) VALUES (?,?, ?, ?)");
            preparedStatement.setInt(1,useregister.getId());
            preparedStatement.setString(2, useregister.getUsername());
            preparedStatement.setString(3, useregister.getPassword());
            preparedStatement.setString(4, useregister.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jobDetails(Jobposting jobposting) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  jobposting(Empid,Compname,Comploc,Jobskill,Expneed) VALUES (?,?, ?, ?,?)");
            preparedStatement.setInt(1, jobposting.getEmpid());
            preparedStatement.setString(2, jobposting.getCompname());
            preparedStatement.setString(3, jobposting.getComploc());
            preparedStatement.setString(4, jobposting.getJobskill());
            preparedStatement.setInt(5, jobposting.getExpneed());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void employeeDetails(Employeereg employeereg) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO Employees(empname,emppassword,empemail,empphno) VALUES (?,?, ?, ?)");
            preparedStatement.setString(1,employeereg.getEmpname());
            preparedStatement.setString(2,employeereg.getEmppassword());
            preparedStatement.setString(3,employeereg.getEmpemail());
            preparedStatement.setString(4,employeereg.getEmpphno());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean validateUser(String vemail,String vpassword) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM jobseeker WHERE Password=? AND Email=?");
            preparedStatement.setString(2,vpassword);
            preparedStatement.setString(1,vemail);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                String cvpassword=resultSet.getString("vpassword");
                String cvemail=resultSet.getString("vemail");
                if(vemail==cvemail && vpassword==cvpassword)
                {
                    return true;
                }
                return true;
            }
            else{
                System.out.println("Password not matched,No user found try to register again");
            }
        }
        catch (SQLException e){
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
    public boolean isValidemail(String validemail){
        String emailpattern = "^[a-zA-Z0-9_]+@gmail\\\\.com$";
        return validemail.matches(emailpattern);
    }



    @Override
    public boolean validateemployee(String vempemail,String vemppassword) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE emppassword=? AND empemail=?");
            preparedStatement.setString(1,vempemail);
            preparedStatement.setString(2,vemppassword);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                String one=resultSet.getString("vpassword");
                String two=resultSet.getString("vemail");
                if(vemppassword==one && vempemail==two)
                {
                    return true;
                }
                return true;
            }
            else {
                System.out.println("password is not matched and no employee found ,register again");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
