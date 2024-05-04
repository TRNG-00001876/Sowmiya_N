package org.example.model;

public class Jobposting {
    private String role;
    private String required;
    private String experience;
    private String lpa;

    public Jobposting(String role, String required, String experience, String lpa) {
        this.role = role;
        this.required = required;
        this.experience = experience;
        this.lpa = lpa;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLpa() {
        return lpa;
    }

    public void setLpa(String lpa) {
        this.lpa = lpa;
    }
}
