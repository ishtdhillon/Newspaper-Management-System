package com.example.javafx.datepicker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.scene.image.ImageView;

public class DatePickerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker doDOB;

    @FXML
    private TextField showdate;

    @FXML
    private ImageView imgid;

    @FXML
    private Label lblpath;

    @FXML
    private RadioButton radcse;

    @FXML
    private RadioButton radece;


    @FXML
    void showbr(ActionEvent event)
    {
        if(radcse.isSelected())
            lblpath.setText("CSE");

        if(radece.isSelected())
            lblpath.setText("ECE");

    }


    @FXML
    void doselectpic(ActionEvent event)
    {
        FileChooser chooser=new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images","*.jpg","*.png"));
        File file=chooser.showOpenDialog(null);
        lblpath.setText(file.getAbsolutePath());

        try
        {
            imgid.setImage(new Image(new FileInputStream(file)));
            new FileInputStream(file);

        }
        catch(FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }


    }


    @FXML
    void doshow(ActionEvent event)
    {
        String dtstring= doDOB.getEditor().getText();
        //System.out.println(dtstring);
        showdate.setText(dtstring);

        LocalDate local=doDOB.getValue();
        System.out.println(local.getDayOfMonth()+"/"+local.getMonth()+"/"+local.getYear());

    }

    @FXML
    void initialize() {
        assert doDOB != null : "fx:id=\"doDOB\" was not injected: check your FXML file 'DatePickerView.fxml'.";
        assert showdate != null : "fx:id=\"showdate\" was not injected: check your FXML file 'DatePickerView.fxml'.";

    }

}
