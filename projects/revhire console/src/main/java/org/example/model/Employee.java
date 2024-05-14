package org.example.model;

public class Employee {
    private String companyName;
    private String gmail;
    private String password;

    public Employee(String companyName, String gmail, String password) {
        this.companyName = companyName;
        this.gmail = gmail;
        this.password = password;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
