package org.example.dao;
import org.example.model.Employee;
import org.example.model.Jobposting;
import org.example.model.Users;
import org.example.model.Resume;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class Daoimpl implements Dao
{
    private static Daoimpl instance;
    private Connection connection;

    private Daoimpl(Connection connection)
    {
        this.connection=connection;
    }

    public static synchronized Daoimpl getInstance(Connection connection)
    {
        if(instance==null)
        {
            instance=new Daoimpl(connection);
        }
        return instance;
    }

    @Override
    public boolean validString(String name)
    {
        if(name==null || name.isEmpty())
        {
            return  false;
        }
        for(char c:name.toCharArray())
        {
            if(! Character.isLetter(c) || c==' ' || c=='/' || c==',' || c=='-')
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean validMail(String mail)
    {
        String pattern="^[a-zA-Z0-9._%+-]+@gmail.com$";
        return mail.matches(pattern);
    }

    public boolean validPassword(String password)
    {
        String pattern=".*[!@#$%^&*()\\\\-_=+\\\\\\\\|\\\\[{\\\\]};:'\\\",<.>/?].*";
        return password.matches(pattern);
    }

    @Override
    public void addUserDetails(Users user_reg)
    {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO USERDATA(name,gmail,password) VALUES (?,?,?)");
            preparedStatement.setString(1,user_reg.getName());
            preparedStatement.setString(2,user_reg.getGmail());
            preparedStatement.setString(3,user_reg.getPassword());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validateUser(String gmail,String password)
    {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * from USERDATA WHERE gmail=? AND password=? ");
            preparedStatement.setString(1,gmail);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                String Cgmail=resultSet.getString("gmail");
                String Cpassword=resultSet.getString("password");
                if(gmail==Cgmail &&password==Cpassword)
                {
                    return true ;
                }
                return true;
            }
            else{
                System.out.println("No record is there just register and login ");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean validateEmployee(String gmail,String password)
    {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * from EMPDATA WHERE gmail=? AND password=? ");
            preparedStatement.setString(1,gmail);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                String Cgmail=resultSet.getString("gmail");
                String Cpassword=resultSet.getString("password");
                if(gmail.equals(Cgmail) && password.equals(Cpassword))
                {
                    return true;
                }
                return true;
            }
            else{
                System.out.println("No record is there just register and login ");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addEmpDetails(Employee employee)
    {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO EMPDATA(companyname,gmail,password) VALUES (?,?,?)");
            preparedStatement.setString(1, employee.getCompanyName());
            preparedStatement.setString(2, employee.getGmail());
            preparedStatement.setString(3, employee.getPassword());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addResume(Resume resume,String gmail)
    {
        try{
            int id=0;
            int cid=0;
            PreparedStatement preparedStatement=connection.prepareStatement("select * from userdata where gmail=?");
            preparedStatement.setString(1,gmail);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next())
            {
                id=resultSet.getInt("id");
            }
            PreparedStatement preparedStatement2=connection.prepareStatement("select id from empresume where id=?");
            preparedStatement2.setInt(1,id);
            ResultSet resultSet1=preparedStatement2.executeQuery();
            if(resultSet1.next())
            {
                cid=resultSet1.getInt("id");
            }
            if(cid==id)
            {
                System.err.println("Already Resume has been Created ");
            }
            else{
                PreparedStatement preparedStatement1=connection.prepareStatement("INSERT INTO EMPRESUME(id,location,skillset1,skillset2,skillset3) VALUES (?,?,?,?,?)");
                preparedStatement1.setInt(1,id);
                preparedStatement1.setString(2,resume.getLocation());
                preparedStatement1.setString(3,resume.getSkillset1());
                preparedStatement1.setString(4,resume.getSkillset2());
                preparedStatement1.setString(5,resume.getSkillset3());
                preparedStatement1.executeUpdate();
                System.out.println("Sucessfully resume created");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void viewResume(String user_name)
    {
        try {
            int id=0;
            String name="";
            PreparedStatement preparedStatement1=connection.prepareStatement("select * from userdata where gmail=?");
            preparedStatement1.setString(1,user_name);
            ResultSet resultSet1=preparedStatement1.executeQuery();
            if(resultSet1.next())
            {
               id=resultSet1.getInt("id");
               name=resultSet1.getString("name");
            }

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM empresume where id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("***********************");
            System.out.println("Resume");
            System.out.println("Name: "+name);
            System.out.println("gmail: "+user_name);
            if(resultSet.next()) {
                System.out.println("id : "+resultSet.getInt("id"));
                System.out.println("location : "+resultSet.getString("location"));
                System.out.println("skillset1 : "+resultSet.getString("skillset1"));
                System.out.println("skillset2 : "+resultSet.getString("skillset2"));
                System.out.println("skillset3 : "+resultSet.getString("skillset3"));
                System.out.println("***********************");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void viewApplication(String mail)
    {
        try{
            int id=0;
            String name="";
            PreparedStatement preparedStatement1=connection.prepareStatement("select * from empdata where gmail=?");
            preparedStatement1.setString(1,mail);
            ResultSet resultSet1=preparedStatement1.executeQuery();
            if(resultSet1.next())
            {
                id=resultSet1.getInt("id");
                name=resultSet1.getString("companyname");
            }
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM job where id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("***********************");
            System.out.println("Application");
            System.out.println("Name: "+name);
            System.out.println("gmail: "+mail);
            while(resultSet.next()) {
                System.out.println("ID : "+resultSet.getInt("id"));
                System.out.println("ROLE : "+resultSet.getString("jobrole"));
                System.out.println("REQUIRED : "+resultSet.getString("required"));
                System.out.println("EXPERIENCE : "+resultSet.getString("experience"));
                System.out.println("LPA : "+resultSet.getString("lpa"));
                System.out.println("***********************");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateResume(String location,String skillset1,String skillset2,String skillset3,String user_name)
    {
        try{
            int id=0;
            PreparedStatement preparedStatement1=connection.prepareStatement("select * from userdata where gmail=?");
            preparedStatement1.setString(1,user_name);
            ResultSet resultSet1=preparedStatement1.executeQuery();
            if(resultSet1.next())
            {
                id=resultSet1.getInt("id");
            }
            PreparedStatement preparedStatement = connection.prepareStatement("update empresume set location=? , skillset1=?, skillset2=?, skillset3=? where id=?");

            preparedStatement.setString(1,location);
            preparedStatement.setString(2,skillset1);
            preparedStatement.setString(3,skillset2);
            preparedStatement.setString(4,skillset3);
            preparedStatement.setInt(5,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateApplication(String oldrole,String role,String required,String experience,String lpa)
    {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("update job set jobrole=? , required=?, experience=?, lpa=? where jobrole=?");
            preparedStatement.setString(1,role);
            preparedStatement.setString(2,required);
            preparedStatement.setString(3,experience);
            preparedStatement.setString(4,lpa);
            preparedStatement.setString(5,oldrole);
            preparedStatement.executeUpdate();
            System.out.println("Updated sucessfully");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteApplication(String gmail)
    {
       try{
           PreparedStatement preparedStatement = connection.prepareStatement("delete FROM job where jobrole=?");
           preparedStatement.setString(1,gmail);
           preparedStatement.executeUpdate();
           System.out.println("Sucessfully Deleted");
       }
       catch (SQLException e)
       {
           e.printStackTrace();
       }
    }

    @Override
    public boolean checkupdateApplication(String role)
    {
        try
        {
            PreparedStatement preparedStatement= connection.prepareStatement("select * from job where jobrole=?");
            preparedStatement.setString(1,role);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return true;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteResume(String user_name)
    {
        try{
            int id=0;
            PreparedStatement preparedStatement1=connection.prepareStatement("select * from userdata where gmail=?");
            preparedStatement1.setString(1,user_name);
            ResultSet resultSet1=preparedStatement1.executeQuery();
            if(resultSet1.next())
            {
                id=resultSet1.getInt("id");
            }
            PreparedStatement preparedStatement = connection.prepareStatement("delete FROM empresume where id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Sucessfully Deleted");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void postJob(Jobposting jobposting, String name)
    {
        try{
            int id=0;
            PreparedStatement preparedStatement=connection.prepareStatement("select * from empdata where gmail=?");
            preparedStatement.setString(1,name);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next())
            {
                id=resultSet.getInt("id");
            }

            PreparedStatement preparedStatement1=connection.prepareStatement("INSERT INTO job(id,jobrole,required,experience,lpa) VALUES (?,?,?,?,?)");
            preparedStatement1.setInt(1,id);
            preparedStatement1.setString(2, jobposting.getRole());
            preparedStatement1.setString(3, jobposting.getRequired());
            preparedStatement1.setString(4, jobposting.getExperience());
            preparedStatement1.setString(5, jobposting.getLpa());
            preparedStatement1.executeUpdate();
            System.out.println("Sucessfully job Post Created");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void listAllJobs()
    {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("select * from job");
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                System.out.println("***********************************************************");
                System.out.println("Role :"+resultSet.getString("jobrole"));
                System.out.println("Required :"+resultSet.getString("required"));
                System.out.println("Experience :"+resultSet.getString("experience"));
                System.out.println("LPA :"+resultSet.getString("lpa"));
                System.out.println("***********************************************************");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void searchByRole(String role)
    {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("select * from job where jobrole=?");
            preparedStatement.setString(1,role);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                System.out.println("***********************************************************");
                System.out.println("Role :"+resultSet.getString("jobrole"));
                System.out.println("Required :"+resultSet.getString("required"));
                System.out.println("Experience :"+resultSet.getString("experience"));
                System.out.println("LPA :"+resultSet.getString("lpa"));
                System.out.println("***********************************************************");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void searchByRequired(String req)
    {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("select * from job where required=?");
            preparedStatement.setString(1,req);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                System.out.println("***********************************************************");
                System.out.println("Role :"+resultSet.getString("jobrole"));
                System.out.println("Required :"+resultSet.getString("required"));
                System.out.println("Experience :"+resultSet.getString("experience"));
                System.out.println("LPA :"+resultSet.getString("lpa"));
                System.out.println("***********************************************************");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkValidForApply(String gmail,String crole)
    {
        try{
            int id=0;
            int jid=0;
            String role="";
            PreparedStatement preparedStatement=connection.prepareStatement("select id from userdata where gmail=?");
            preparedStatement.setString(1,gmail);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                id=resultSet.getInt("id");
            }

            PreparedStatement preparedStatement1=connection.prepareStatement("select * from application where userid=?");
            preparedStatement1.setInt(1,id);
            ResultSet resultSet1=preparedStatement1.executeQuery();
            if(resultSet1.next())
            {
                jid=resultSet1.getInt("empid");
            }

            PreparedStatement preparedStatement2=connection.prepareStatement("select * from job where id=?");
            preparedStatement2.setInt(1,jid);
            ResultSet resultSet2=preparedStatement2.executeQuery();
            if(resultSet2.next())
            {
                role=resultSet2.getString("jobrole");
            }
            if(crole==role)
            {
                return true;
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public void applyForJob(String mail,String name)
    {
        try{
            int id=0;
            int cid=0;
            String status="pending";
            PreparedStatement preparedStatement=connection.prepareStatement("select * from userdata where gmail=?");
            preparedStatement.setString(1,mail);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                id=resultSet.getInt("id");
            }


            PreparedStatement preparedStatement2=connection.prepareStatement("select * from job where jobrole=?");
            preparedStatement2.setString(1,name);
            ResultSet resultSet2=preparedStatement2.executeQuery();
            if(resultSet2.next())
            {
                cid=resultSet2.getInt("id");
            }

            PreparedStatement preparedStatement1=connection.prepareStatement("insert into application(userid,empid,status) values(?,?,?)");
            preparedStatement1.setInt(1,id);
            preparedStatement1.setInt(2,cid);
            preparedStatement1.setString(3,status);
            preparedStatement1.executeUpdate();
            System.out.println("sucessful");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void status(String gmail) {
        try{
            int id=0;
            int jid=0;
            PreparedStatement preparedStatement=connection.prepareStatement("select * from userdata where gmail=?");
            preparedStatement.setString(1,gmail);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                id=resultSet.getInt("id");
                System.out.println("ID : "+id);
            }

            PreparedStatement preparedStatement1=connection.prepareStatement("select * from application where userid=?");
            preparedStatement1.setInt(1,id);
            ResultSet resultSet1=preparedStatement1.executeQuery();
            while(resultSet1.next())
            {
                jid=resultSet1.getInt("empid");
                System.out.println("Company id "+jid);
                System.out.println("Status : "+resultSet1.getString("status"));
            }

            PreparedStatement preparedStatement3=connection.prepareStatement("select * from empdata where id=?");
            preparedStatement3.setInt(1,jid);
            ResultSet resultSet2=preparedStatement3.executeQuery();
            while(resultSet2.next())
            {
                System.out.println("Company name : "+resultSet2.getString("companyname"));
            }
        }
        catch(SQLException e)
        {

        }
    }

    @Override
    public void userlist(String gmail) {
        try{
            int id=0;
            int eid=0;
            PreparedStatement preparedStatement=connection.prepareStatement("select * from empdata where gmail=?");
            preparedStatement.setString(1,gmail);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                id=resultSet.getInt("id");
            }
            PreparedStatement preparedStatement1=connection.prepareStatement("select * from application where userid=?");
            preparedStatement1.setInt(1,id);
            ResultSet resultSet1=preparedStatement1.executeQuery();
            if(resultSet1.next())
            {
                eid=resultSet1.getInt("userid");
            }
            System.out.println("User ID "+eid);
            PreparedStatement preparedStatement2=connection.prepareStatement("select * from userdata where id=?");
            preparedStatement2.setInt(1,eid);
            ResultSet resultSet2=preparedStatement2.executeQuery();
            while(resultSet2.next())
            {
                System.out.println("Gmail :"+resultSet2.getString("gmail"));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void accpetOrReject(String status,String mail)
    {
        int ids = 0;
        try{

            PreparedStatement preparedStatement1=connection.prepareStatement("select id from empdata where gmail=?");
            preparedStatement1.setString(1,mail);
            ResultSet resultSet=preparedStatement1.executeQuery();
            if(resultSet.next())
            {
                ids=resultSet.getInt("id");
                System.out.println(ids);
            }
            PreparedStatement preparedStatement=connection.prepareStatement("update application set status=? where userid=?");
            preparedStatement.setString(1,status);
            preparedStatement.setInt(2,ids);
          int  a1  = preparedStatement.executeUpdate();
           if(a1>0)
           {
              System.out.println("Updated sucessfully");
           }
           else
           {
                System.out.println("Not updated");
           }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
