package org.example.service;

import org.example.dao.Dao;
import org.example.model.Employeereg;
import org.example.model.Useregister;
import org.example.model.Resume;
import org.example.model.Jobposting;


public class Service {
    private Dao dao;

    public Service(Dao dao) {
        this.dao = dao;
    }

    public void userDetails(Useregister useregister) {
        dao.userDetails(useregister);
    }

    public void jobDetails(Jobposting jobposting) {
        dao.jobDetails(jobposting);
    }

    public void employeeDetails(Employeereg employeereg){
        dao.employeeDetails((employeereg));
    }

    public boolean validateUser(String vemail,String vpassword){

        return dao.validateUser(vemail,vpassword);
    }

    public boolean validateemployee(String vempemail,String vemppassword){
        return dao.validateemployee(vempemail,vemppassword);
    }

    public boolean isValidpassword(String validpassword) {

        return dao.isValidpassword(validpassword);
    }

    public boolean isValidemail(String validemail){

        return dao.isValidemail(validemail);
    }

    public boolean isValidusername(String validusername){
        return dao.isValidusername(validusername);
    }

    //resume

    public boolean addResume(Resume resume, String userEmail) {
        return dao.addResume(resume, userEmail);
    }

    public boolean updateResume(Resume resume, String userEmail) {
        return dao.updateResume(resume, userEmail);
    }

    public boolean deleteResume(String userEmail) {
        return dao.deleteResume(userEmail);
    }

    public Resume getResume(String userEmail) {
        return dao.getResume(userEmail);
    }





}
