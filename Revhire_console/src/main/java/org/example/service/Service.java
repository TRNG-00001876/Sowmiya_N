package org.example.service;

import org.example.dao.Dao;
import org.example.model.Employeereg;
import org.example.model.Useregister;
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

}
