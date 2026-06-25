package com.example.javafx.admin;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.javafx.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtusername;

    @FXML
    void doLogin(ActionEvent event)
    {
        String username = txtusername.getText();
        String password = txtpassword.getText();

        if (username.equals("Ishtpreet") && password.equals("java"))
        {
            try{
                Stage stage=new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard/DashboardView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Hello!");
                stage.setScene(scene);
                stage.show();

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void initialize()
    {
        assert txtpassword != null : "fx:id=\"txtpassword\" was not injected: check your FXML file 'AdminView.fxml'.";
        assert txtusername != null : "fx:id=\"txtusername\" was not injected: check your FXML file 'AdminView.fxml'.";

    }

}
