package com.example.javafx.papermaster;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.example.javafx.jdbcc.MysqlDBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class PaperMasterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtlang;

    @FXML
    private ComboBox<String> txtpaper;

    @FXML
    private TextField txtprice;

    @FXML
    void createnew(ActionEvent event)
    {
        txtlang.setText("");
        txtprice.setText("");
        txtpaper.setValue("");

    }

    @FXML
    void doDelete(ActionEvent event)
    {
        String query="delete from papermaster where paper=?";
        try
        {
            PreparedStatement pst=con.prepareStatement(query);

            pst.setString(1,(txtpaper.getValue()));

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
    void doSave(ActionEvent event)
    {
        String query="insert into papermaster values(?,?,?)";
        try
        {
            PreparedStatement pst=con.prepareStatement(query);

            pst.setString(1,txtpaper.getValue());
            pst.setString(2,txtlang.getText());
            pst.setFloat(3,Float.parseFloat(txtprice.getText()));

            pst.executeUpdate();
            System.out.println("Record Saved");

        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }

    }

    Connection con;
    @FXML
    void doUpdate(ActionEvent event)
    {
        String query="update papermaster set language=?, price=? where paper=?";
        try
        {
            PreparedStatement pst=con.prepareStatement(query);

            pst.setString(1,txtlang.getText());
            pst.setFloat(2,Float.parseFloat(txtprice.getText()));

            pst.setString(3,txtpaper.getValue());

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
    void dofind(ActionEvent event)
    {
        try
        {
            PreparedStatement stmt=con.prepareStatement("select * from papermaster where paper=?");
            stmt.setString(1,(txtpaper.getValue()));
            ResultSet res=stmt.executeQuery();

            if(res.next()==true)
            {
                String paper=res.getString("paper");
                String language=res.getString("language");
                float price=res.getFloat("price");

                txtpaper.setValue(paper);
                txtlang.setText(language);
                txtprice.setText(String.valueOf(price));
                System.out.println(paper+" "+language+" "+price);
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

    ArrayList<String> showpapers()
    {
        ArrayList<String> pap=new ArrayList<>();

        try
        {
            PreparedStatement stmt=con.prepareStatement("select distinct paper from papermaster");

            ResultSet res=stmt.executeQuery();

            while(res.next())
            {
                String paper=res.getString("paper");
                pap.add(paper);
            }
            System.out.println(pap);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return pap;
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

        ArrayList<String> arlist=showpapers();
        for(String s : arlist)
        {
            txtpaper.getItems().add(s);
        }

    }

}
