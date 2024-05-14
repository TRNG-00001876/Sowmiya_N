package org.example.dao;

import org.example.model.Employee;
import org.example.model.Jobposting;
import org.example.model.Users;
import org.example.model.Resume;

public interface Dao
{
    boolean validString(String name);

    boolean validMail(String mail);

    boolean validPassword(String password);

    void addUserDetails(Users user_reg);

    boolean validateUser(String gmail,String password);

    void addEmpDetails(Employee employee);

    boolean validateEmployee(String gmail,String password);

    void addResume(Resume resume,String name);

    void viewResume(String user_name);

    void updateResume(String location,String skillset1,String skillset2,String skillset3,String user_name);

    void deleteResume(String user_name);

    void postJob(Jobposting jobposting, String name);

    void viewApplication(String mail);

    boolean checkupdateApplication(String role);

    void updateApplication(String gmail,String role,String required,String experience,String lpa);

    void deleteApplication(String gmail);

    void listAllJobs();

    void searchByRole(String role);

    void searchByRequired(String req);

    boolean checkValidForApply(String gmail,String role);

    void applyForJob(String mail,String name);

    void status(String gmail);

    void userlist(String gmail);

    void accpetOrReject(String status,String mail);

}
