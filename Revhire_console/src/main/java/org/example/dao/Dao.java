package org.example.dao;
import org.example.model.Employeereg;
import org.example.model.Resume;
import org.example.model.Useregister;
import org.example.model.Jobposting;

import java.util.List;


public interface Dao {
    void userDetails(Useregister useregister);
    void jobDetails(Jobposting jobposting);
    void employeeDetails(Employeereg employeereg);
    boolean validateUser(String vemail,String vpassword);
    boolean validateemployee(String vempemail,String vemppassword);
    boolean isValidpassword(String validpassword);
    boolean isValidemail(String validemail);
    boolean isValidusername(String validusername);
    //search job
    List<Jobposting> getAllJobPostings();
    List<Jobposting> getFilteredJobPostings(String filterCriteria);
    //validation string for jobposting
    boolean validString(String details);
    boolean validInt(int detail);
    //resume crud
    boolean addResume(Resume resume, String userEmail);
    boolean updateResume(Resume resume, String userEmail);
    boolean deleteResume(String userEmail);
    Resume getResume(String userEmail);


}
