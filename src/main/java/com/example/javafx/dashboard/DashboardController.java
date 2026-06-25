package com.example.javafx.dashboard;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.javafx.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;

public class DashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    void OpenAreas(ActionEvent event)
    {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("areas/AreasView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Areas");
            stage.setScene(scene);
            stage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void OpenBIllBoard(ActionEvent event)
    {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("billboard/BillBoardView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Bill Board");
            stage.setScene(scene);
            stage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void OpenBIllCollector(ActionEvent event)
    {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("billcollector/BillCollectorView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Bill Collection");
            stage.setScene(scene);
            stage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void OpenBilling(ActionEvent event)
    {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("billing/BillingView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Billing");
            stage.setScene(scene);
            stage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void OpenCustomerBoard(ActionEvent event)
    {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerboard/CustomerBoardView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Customer Board");
            stage.setScene(scene);
            stage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void OpenCustomerEnrollment(ActionEvent event)
    {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customers/CustomersView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Customers");
            stage.setScene(scene);
            stage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void OpenHawkers(ActionEvent event)
    {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hawkers/HawkersView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hawkers");
            stage.setScene(scene);
            stage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void OpenPaperMaster(ActionEvent event)
    {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("papermaster/PaperMasterView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Paper Master");
            stage.setScene(scene);
            stage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void OpenChart(ActionEvent event)
    {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("charts/ChartsView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Chart Analysis");
            stage.setScene(scene);
            stage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void initialize()
    {

    }

}
