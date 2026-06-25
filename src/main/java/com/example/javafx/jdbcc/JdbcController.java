package com.example.javafx.jdbcc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class JdbcController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnbrowser;

    @FXML
    private ComboBox<String> comboeid;

    @FXML
    private DatePicker dob;

    @FXML
    private ImageView previmg;

    @FXML
    private TextField txtage;

    @FXML
    private TextField txtname;

    @FXML
    void doClear(ActionEvent event)
    {
    }

    ArrayList<String> showEmpID()
    {
        ArrayList<String> eids=new ArrayList<>();

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
        return eids;
    }

    @FXML
    void doDelete(ActionEvent event)
    {
        String query="delete from employees where eid=?";
        try
        {
            PreparedStatement pst=con.prepareStatement(query);

            pst.setInt(1,Integer.parseInt(comboeid.getValue()));

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

    @FXML
    void doFind(ActionEvent event)
    {
        try
        {
            PreparedStatement stmt=con.prepareStatement("select * from employees where eid=?");
            stmt.setInt(1,Integer.parseInt(comboeid.getValue()));
            ResultSet res=stmt.executeQuery();

            if(res.next()==true)
            {
                int eid=res.getInt("eid");
                String name=res.getString("name");
                float age=res.getFloat("age");
                Date dt=res.getDate("dob");
                String picp=res.getString("picpath");

                txtage.setText(String.valueOf(eid));
                txtname.setText(name);
                dob.setValue(dt.toLocalDate());
                previmg.setImage(new Image(new FileInputStream(new File(picp))));
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

    String picp;
    @FXML
    void doBrowse(ActionEvent event)
    {
        FileChooser chooser=new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images","*.jpg","*.png"));
        File file=chooser.showOpenDialog(null);
        picp=file.getAbsolutePath();

        try
        {
            previmg.setImage(new Image(new FileInputStream(file)));
            new FileInputStream(file);

        }
        catch(FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }


    }

    Connection con;

    @FXML
    void doSave(ActionEvent event)
    {
        String query="insert into employees values(?,?,?,?,?)";
        try
        {
            PreparedStatement pst=con.prepareStatement(query);
            pst.setInt(1,Integer.parseInt(comboeid.getValue()));
            pst.setString(2,txtname.getText());
            pst.setFloat(3,Float.parseFloat(txtage.getText()));

            LocalDate lcl=dob.getValue();
            java.sql.Date dt=java.sql.Date.valueOf(lcl);
            pst.setDate(4,dt);

            pst.setString(5,picp);
            pst.executeUpdate();
            System.out.println("Record Saved");

        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }

    }

    @FXML
    void doUpdate(ActionEvent event)
    {
        String query="update employees set name=?, age=?, dob=?, picpath=? where eid=?";
        try
        {
            PreparedStatement pst=con.prepareStatement(query);

            pst.setString(1,txtname.getText());
            pst.setFloat(2,Float.parseFloat(txtage.getText()));

            LocalDate lcl=dob.getValue();
            java.sql.Date dt=java.sql.Date.valueOf(lcl);
            pst.setDate(3,dt);

            pst.setString(4,picp);

            pst.setInt(5,Integer.parseInt(comboeid.getValue()));

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

    @FXML
    void initialize()
    {
        con = MysqlDBConnection.getMysqlDBConnection();
        if(con==null)
        {
            System.out.println("Connection Error");
            return;
        }

        ArrayList<String> arlist=showEmpID();
        for(String s : arlist)
        {
            comboeid.getItems().add(s);
        }


    }

}
