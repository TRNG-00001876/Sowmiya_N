package org.example.model;

public class Jobposting {
    private String Compname;
    private String Comploc;
    private String Jobskill;
    private int Expneed;

    public Jobposting(String Compname, String Comploc, String Jobskill, int Expneed) {
        this.Compname = Compname;
        this.Comploc = Comploc;
        this.Jobskill= Jobskill;
        this.Expneed=Expneed;
    }


    public String getCompname() {
        return Compname;
    }

    public void setCompname(String Compname) {
        this.Compname = Compname
        ;
    }

    public String getComploc() {
        return Comploc;
    }

    public void setComploc(String Comploc) {
        this.Comploc = Comploc;
    }

    public String getJobskill() {
        return Jobskill;
    }

    public void setJobskill(String Jobskill) {
        this.Jobskill = Jobskill;
    }

    public int getExpneed() {
        return Expneed;
    }

    public void setExpneed(int Expneed) {
        this.Expneed = Expneed;
    }
}
