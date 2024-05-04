package org.example.service;

import org.example.dao.Dao;
import org.example.model.Jobposting;
import org.example.model.Resume;
import org.example.model.Users;
import org.example.model.Employee;

public class Service {
    private Dao dao;



    public Service(Dao dao)
    {
        this.dao = dao;
    }

    public boolean validString(String name)
    {
        return dao.validString(name);
    }

    public boolean validMail(String mail)
    {
        return dao.validMail(mail);
    }

    public boolean validPassword(String password)
    {
        return dao.validPassword(password);
    }

    public void addUserDetails(Users user_reg)
    {
        dao.addUserDetails(user_reg);
    }

    public boolean validateUser(String gmail,String password)
    {
         return dao.validateUser(gmail,password);
    }

    public void addEmpDetails(Employee employee)
    {
        dao.addEmpDetails(employee);
    }

    public boolean validateEmployee(String gmail,String paaword)
    {
        return dao.validateEmployee(gmail, paaword);
    }

    public void addResume(Resume resume,String gmail)
    {
        dao.addResume(resume,gmail);
    }

    public void viewResume(String user_name)
    {
       dao.viewResume(user_name);
    }

    public void updateResume(String location,String skillset1,String skillset2,String skillset3,String user_name)
    {
        dao.updateResume(location,skillset1,skillset2,skillset3,user_name);
    }

    public void deleteResume(String user_name)
    {
        dao.deleteResume(user_name);
    }

    public void postJob(Jobposting jobposting, String name)
    {
        dao.postJob(jobposting,name);
    }

    public void viewApplication(String mail)
    {
        dao.viewApplication(mail);
    }

    public boolean checkupdateApplication(String role)
    {
        return dao.checkupdateApplication(role);
    }

    public void updateApplication(String gmail,String role,String required,String experience,String lpa)
    {
        dao.updateApplication(gmail,role,required,experience,lpa);
    }

    public void deleteApplication(String gmail)
    {
        dao.deleteApplication(gmail);
    }

    public void listAllJobs()
    {
        dao.listAllJobs();
    }

    public void searchByRole(String role)
    {
        dao.searchByRole(role);
    }

    public void searchByRequired(String req)
    {
        dao.searchByRequired(req);
    }

    public boolean checkValidForApply(String gmail,String crole)
    {
        return dao.checkValidForApply(gmail,crole);
    }

    public void applyForJob(String mail,String name)
    {
        dao.applyForJob(mail,name);
    }

    public void status(String gmail){
        dao.status(gmail);
    }

    public void userlist(String gmail){
        dao.userlist(gmail);
    }

    public void accpetOrReject(String status,String mail)
    {
        dao.accpetOrReject(status,mail);
    }

}
