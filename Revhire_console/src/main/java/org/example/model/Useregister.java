package org.example.model;

public class Useregister {
    private String Username;
    private String Password;
    private String Email;

    public Useregister( String Username, String Password,String Email) {
        this.Username = Username;
        this.Password = Password;
        this.Email= Email;
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
