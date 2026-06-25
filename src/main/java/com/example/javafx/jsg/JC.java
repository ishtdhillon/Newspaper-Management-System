package com.example.javafx.jsg;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

public class JC {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txta;

    @FXML
    private TextField txtb;

    @FXML
    private TextField txtmulti;

    @FXML
    private TextField txtsum;

    @FXML
    private CheckBox chkdsa;

    @FXML
    private CheckBox chkjava;

    @FXML
    private CheckBox chkmern;

    @FXML
    private TextField tot;

    @FXML
    void dototal(ActionEvent event)
    {
        String allTech="";
        int total=0;

        if(chkdsa.isSelected())
        {
            allTech += chkdsa.getText() + ",";
            total+=8000;
        }
        if(chkjava.isSelected())
        {
            allTech += chkjava.getText() + ",";
            total+=9000;
        }
        if(chkmern.isSelected())
        {
            allTech += chkmern.getText() + ",";
            total+=10000;
        }
        tot.setText(String.valueOf(total));

    }


    @FXML
    void domulti(ActionEvent event)
    {
        try
        {
            int a=Integer.parseInt(txta.getText());
            float b= Float.parseFloat(txtb.getText());
            float mul= a*b;
            txtmulti.setText(String.valueOf(mul));
        }
        catch(Exception exp)
        {
            showMsg(exp.getMessage());
        }

    }

    @FXML
    void dosaaf(ActionEvent event)
    {
        txta.setText("");
        txtb.setText("");
        txtsum.setText("");
        txtmulti.setText("");
        tot.setText("");
        chkdsa.setSelected(false);
        chkjava.setSelected(false);
        chkmern.setSelected(false);

    }

    @FXML
    void dosum(ActionEvent event)
    {
        try
        {
            int a=Integer.parseInt(txta.getText());
            float b= Float.parseFloat(txtb.getText());
            float sum= a + b;
            txtsum.setText(String.valueOf(sum));
        }
        catch(Exception exp)
        {
            showMsg(exp.getMessage());
        }

    }

    void 	showMsg(String msg)
    {
        //Alert alert = new Alert(AlertType.INFORMATION);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        //Alert alert = new Alert(AlertType.WARNING);

        alert.setTitle("Information Dialog");
        //or
        //alert.setTitle(null);


        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(msg);

        alert.showAndWait();
    }

    @FXML
    void initialize() {
        assert txta != null : "fx:id=\"txta\" was not injected: check your FXML file 'JV.fxml'.";
        assert txtb != null : "fx:id=\"txtb\" was not injected: check your FXML file 'JV.fxml'.";
        assert txtmulti != null : "fx:id=\"txtmulti\" was not injected: check your FXML file 'JV.fxml'.";
        assert txtsum != null : "fx:id=\"txtsum\" was not injected: check your FXML file 'JV.fxml'.";

    }

}
