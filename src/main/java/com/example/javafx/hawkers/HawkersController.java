package com.example.javafx.hawkers;

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

import com.example.javafx.jdbcc.MysqlDBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.scene.image.ImageView;

public class HawkersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField adhcard;

    @FXML
    private ComboBox<String> comboarea;

    @FXML
    private ComboBox<String> combohid;

    @FXML
    private DatePicker doj;

    @FXML
    private TextField selarea;

    @FXML
    private TextField txtaddr;

    @FXML
    private TextField txtcont;

    @FXML
    private TextField txtname;

    @FXML
    private ImageView previmg;

    String picp;
    @FXML
    void doAddImage(ActionEvent event)
    {
        FileChooser chooser=new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images","*.jpg","*.png"));
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

    @FXML
    void doSelArea(ActionEvent event)
    {
        selarea.setText(selarea.getText()+comboarea.getValue()+",");
    }

    @FXML
    void doClear(ActionEvent event)
    {
        combohid.setValue("");
        comboarea.setValue("");
        txtname.setText("");
        txtname.setDisable(false);
        txtcont.setText("");
        txtcont.setDisable(false);
        txtaddr.setText("");
        adhcard.setText("");
        adhcard.setDisable(false);
        selarea.setText("");
        doj.setValue(null);
        previmg.setImage(null);
        picp=null;

    }

    @FXML
    void doDelete(ActionEvent event)
    {
        String query="delete from hawkers where hawkerid=?";
        try
        {
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,combohid.getValue());

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

        combohid.getItems().remove(combohid.getValue());

    }

    @FXML
    void doFind(ActionEvent event)
    {
        try
        {
            PreparedStatement stmt=con.prepareStatement("select * from hawkers where hawkerid=?");
            stmt.setString(1,combohid.getValue());
            ResultSet res=stmt.executeQuery();

            if(res.next()==true)
            {
                String name=res.getString("name");
                String contact=res.getString("contact");
                Date dt=res.getDate("doj");
                String adhaar=res.getString("adhaar");
                String address=res.getString("address");
                String picp=res.getString("picpath");


                txtname.setText(name);
                txtname.setDisable(true);
                txtcont.setText(contact);
                txtcont.setDisable(true);
                doj.setValue(dt.toLocalDate());
                adhcard.setText(adhaar);
                adhcard.setDisable(true);
                txtaddr.setText(address);
                previmg.setImage(new Image(new FileInputStream(new File(picp))));


                String SelArea = res.getString("selareas");
                selarea.setText(SelArea);


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

    @FXML
    void doModify(ActionEvent event)
    {
        String query="update hawkers set doj=?, address=?, picpath=?, selareas=? where hawkerid=?";
        try
        {
            PreparedStatement pst=con.prepareStatement(query);

            LocalDate lcl=doj.getValue();
            java.sql.Date dt=java.sql.Date.valueOf(lcl);
            pst.setDate(1,dt);

            pst.setString(2,txtaddr.getText());

            pst.setString(3,picp);
            pst.setString(4,selarea.getText());
            pst.setString(5,combohid.getValue());


            int count= pst.executeUpdate();
            if(count==0)
                System.out.println("Invalid ID");
            else
                System.out.println("Record Modified");

        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }


    }

    Connection con;
    @FXML
    void doSave(ActionEvent event)
    {
        String query="insert into hawkers values(?,?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement pst=con.prepareStatement(query);

            String name=txtname.getText();
            String firstName = name.split(" ")[0];
            String contact= txtcont.getText();
            String last5=contact.substring(contact.length() - 5);

            String hawkerid = firstName + last5;

            pst.setString(1,hawkerid);
            pst.setString(2,name);
            pst.setString(3,contact);

            LocalDate lcl=doj.getValue();
            java.sql.Date dt=java.sql.Date.valueOf(lcl);
            pst.setDate(4,dt);

            pst.setString(5,adhcard.getText());
            pst.setString(6,txtaddr.getText());

            pst.setString(7,picp);
            pst.setString(8,selarea.getText());
            pst.executeUpdate();
            System.out.println("Record Saved");

        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }

    }

    ArrayList<String> getHawkerID(){
        ArrayList<String> hids=new ArrayList<String>();

        try
        {
            PreparedStatement stmt = con.prepareStatement("select hawkerid from hawkers");
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
                String hawkerID=res.getString("hawkerid");
                hids.add(hawkerID);

            }
            System.out.println(hids);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return hids;
    }

    ArrayList<String> getAllAreas(){
        ArrayList<String> Areas=new ArrayList<String>();

        try
        {
            PreparedStatement stmt = con.prepareStatement("select area from areas");
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
                String area=res.getString("area");
                Areas.add(area);

            }
            System.out.println(Areas);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return Areas;
    }

    Image img;

    @FXML
    void initialize()
    {
        con = MysqlDBConnection.getMysqlDBConnection();
        if(con==null)
        {
            System.out.println("Connection Error");
            return;
        }

        ArrayList<String> lstAreas=getAllAreas();
        for(String str: lstAreas)
        {
            comboarea.getItems().add(str);
        }

        ArrayList<String> lstHawkerID=getHawkerID();
        for(String str: lstHawkerID)
        {
            combohid.getItems().add(str);
        }

        img=previmg.getImage();

    }

}
