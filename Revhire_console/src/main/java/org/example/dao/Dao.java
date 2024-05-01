package org.example.dao;
import org.example.model.Employeereg;
import org.example.model.Useregister;
import org.example.model.Jobposting;

public interface Dao {
    void userDetails(Useregister useregister);
    void jobDetails(Jobposting jobposting);
    void employeeDetails(Employeereg employeereg);
    boolean validateUser(String vemail,String vpassword);
    boolean validateemployee(String vempemail,String vemppassword);
    boolean isValidpassword(String validpassword);
    boolean isValidemail(String validemail);
    boolean isValidusername(String validusername);
}
