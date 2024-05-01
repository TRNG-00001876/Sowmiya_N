package org.example.model;

public class Employeereg {
    private String empname;
    private String emppassword;
    private String empemail;


    public Employeereg(String empname, String emppassword, String empemail) {
        this.empname = empname;
        this.emppassword = emppassword;
        this.empemail = empemail;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getEmppassword() {
        return emppassword;
    }

    public void setEmppassword(String emppassword) {
        this.emppassword = emppassword;
    }

    public String getEmpemail() {
        return empemail;
    }

    public void setEmpemail(String empemail) {
        this.empemail = empemail;
    }
}

