package org.example.model;

public class Useregister {
    private int Userid;
    private String Username;
    private String Password;
    private String Email;

    public Useregister( int Userid,String Username, String Password,String Email) {
        this.Userid=Userid;
        this.Username = Username;
        this.Password = Password;
        this.Email= Email;
    }

    public int getId() {
        return Userid;
    }

    public void setId(int id) {
        this.Userid = Userid;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }



}
