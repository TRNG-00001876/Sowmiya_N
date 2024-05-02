package org.example.model;

public class Useregister {
    private String Username;
    private String Email;
    private String Password;

    public Useregister( String Username, String Email,String Password) {
        this.Username = Username;
        this.Email= Email;
        this.Password = Password;
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
