package com.example.javafx.areas;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import com.example.javafx.jdbcc.MysqlDBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AreasController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtarea;

    Connection con;
    @FXML
    void doSave(ActionEvent event)
    {
        String query="insert into areas values(?)";
        try
        {
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,txtarea.getText());

            pst.executeUpdate();
            System.out.println("Record Saved");

        }
        catch(Exception exp)
        {
            System.out.println(exp.toString());
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


    }

}
