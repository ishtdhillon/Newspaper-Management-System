package com.example.javafx.jsg;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class JsgController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblmsg;

    @FXML
    void dohellofx(ActionEvent event)
    {
        lblmsg.setText("bale bale");

    }

    @FXML
    void initialize() {
        assert lblmsg != null : "fx:id=\"lblmsg\" was not injected: check your FXML file 'JsgView.fxml'.";

    }

}
