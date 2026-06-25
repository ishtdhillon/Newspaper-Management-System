package com.example.javafx.listcombo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class ListComboController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combocate;

    @FXML
    private ListView<String> itemid;

    @FXML
    private TextField lblbill;

    @FXML
    private ListView<String> priceid;

    @FXML
    private ListView<String> selitemid;

    @FXML
    private ListView<String> selpriceid;

    @FXML
    private ListView<String> selpriceid1;

    @FXML
    void dobilling(ActionEvent event)
    {
        int bill=0;
        ObservableList<String> itmprice= selpriceid.getItems();
        for(String prc : itmprice)
        {
            bill+= Integer.parseInt(prc);
        }
        lblbill.setText(String.valueOf(bill));

    }

    @FXML
    void dosend(ActionEvent event)
    {
    }



    @FXML
    void doaddincart(MouseEvent event)
    {
        if(event.getClickCount()==2)
        {
            String selitem=itemid.getSelectionModel().getSelectedItem();
            selitemid.getItems().add(selitem);

            int ind=itemid.getSelectionModel().getSelectedIndex();
            priceid.getSelectionModel().select(ind);

            selpriceid.getItems().add(priceid.getSelectionModel().getSelectedItem());

        }

    }

    @FXML
    void dodeleteselitems(MouseEvent event)
    {
        if(event.getClickCount()==2)
        {
            selitemid.getItems().remove(selitemid.getSelectionModel().getSelectedIndex());

        }

    }


    String[] pm={"onion","capsicum","corn"};
    String[] pmp={"100","120","180"};
    String[] bm={"aloo tikki","noodle","cheese"};
    String[] bmp={"70","100","130"};

    @FXML
    void dofillitems(ActionEvent event)
    {
        int selindex=combocate.getSelectionModel().getSelectedIndex();

        itemid.getItems().clear();
        priceid.getItems().clear();

        if(selindex==1)
        {
            itemid.getItems().addAll(pm);
            priceid.getItems().addAll(pmp);
        }
        if(selindex==2)
        {
            itemid.getItems().addAll(bm);
            priceid.getItems().addAll(bmp);
        }

    }


    @FXML
    void initialize() {
        combocate.getItems().add("select");
        combocate.getItems().add("pizza");
        combocate.getItems().add("burger");
        combocate.getSelectionModel().select(0);

        selpriceid.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }

}
