package com.example.javafx.jdbcc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

class Curd
{
    Connection con;

    Curd()
    {
        con = MysqlDBConnection.getMysqlDBConnection();
        if(con==null)
        {
            System.out.println("Connection Error");
            return;
        }
    }
    //create table employees(eid int primary key, name varchar(20), age float, dob date, picpath varchar(200));
    void doSave()
    {
        String query="insert into employees values(?,?,?,?,?)";
        try
        {
            PreparedStatement pst=con.prepareStatement(query);
            pst.setInt(1,103);
            pst.setString(2,"Chaman");
            pst.setFloat(3,43);

            LocalDate lcl=LocalDate.now();
            java.sql.Date dt=java.sql.Date.valueOf(lcl);
            pst.setDate(4,dt);

            pst.setString(5,"yespic.jpg");
            pst.executeUpdate();
            System.out.println("Record Saved");

        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }
    }

    void doUpdate()
    {
        String query="update employees set name=?, age=?, dob=?, picpath=? where eid=?";
        try
        {
            PreparedStatement pst=con.prepareStatement(query);

            pst.setString(1,"Aman Singh");
            pst.setFloat(2,46);

            LocalDate lcl=LocalDate.now();
            java.sql.Date dt=java.sql.Date.valueOf(lcl);
            pst.setDate(3,dt);

            pst.setString(4,"yespic.jpg");

            pst.setInt(5,101);

            int count= pst.executeUpdate();
            if(count==0)
                System.out.println("Invalid ID");
            else
                System.out.println("Record Updated");

        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }
    }

    void doDelete()
    {
        String query="delete from employees where eid=?";
        try
        {
            PreparedStatement pst=con.prepareStatement(query);

            pst.setInt(1,101);

            int count= pst.executeUpdate();
            if(count==0)
                System.out.println("Invalid ID");
            else
                System.out.println("Record Deleted");

        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }
    }

    void showAll()
    {
        try
        {
            PreparedStatement stmt=con.prepareStatement("select * from employees");
            ResultSet res=stmt.executeQuery();

            while(res.next())
            {
                int eid=res.getInt("eid");
                String name=res.getString("name");
                float age=res.getFloat("age");
                Date dt=res.getDate("dob");
                String picp=res.getString("picpath");

                System.out.println(eid+" "+name+" "+age+" "+dt.toString()+" "+picp);
            }

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }

    void showOne()
    {
        try
        {
            PreparedStatement stmt=con.prepareStatement("select * from employees where eid=?");
            stmt.setInt(1,101);
            ResultSet res=stmt.executeQuery();

            if(res.next()==true)
            {
                int eid=res.getInt("eid");
                String name=res.getString("name");
                float age=res.getFloat("age");
                Date dt=res.getDate("dob");
                String picp=res.getString("picpath");

                System.out.println(eid+" "+name+" "+age+" "+dt.toString()+" "+picp);
            }
            else
            {
                System.out.println("Invalid ID");
            }

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }

    void showEmpID()
    {
        ArrayList<String> eids=new ArrayList<String>();

        try
        {
            PreparedStatement stmt=con.prepareStatement("select distinct eid from employees");

            ResultSet res=stmt.executeQuery();

            while(res.next())
            {
                int eid=res.getInt("eid");
                eids.add(String.valueOf(eid));
            }
            System.out.println(eids);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }
}

public class JdbcCurd
{
    public static void main(String args[])
    {
       Curd obj=new Curd();
      // obj.doSave();
        // obj.doUpdate();
        //obj.doDelete();
        //obj.showAll();
        //obj.showOne();
        obj.showEmpID();
    }
}
