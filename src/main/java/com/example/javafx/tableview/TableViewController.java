package com.example.javafx.tableview;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.example.javafx.jdbcc.MysqlDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<PaperMasterBean> tblview;

    @FXML
    void doFetch(ActionEvent event)
    {
        TableColumn<PaperMasterBean, String> pap=new TableColumn<>("Papers");
        pap.setCellValueFactory(new PropertyValueFactory<>("paper"));
        pap.setMinWidth(150);

        TableColumn<PaperMasterBean, String> lang=new TableColumn<>("Languages");
        lang.setCellValueFactory(new PropertyValueFactory<>("language"));
        lang.setMinWidth(100);

        TableColumn<PaperMasterBean, String> prc=new TableColumn<>("Prices");
        prc.setCellValueFactory(new PropertyValueFactory<>("price"));
        prc.setMinWidth(100);

        tblview.getColumns().addAll(pap,lang,prc);

        tblview.setItems(null);
        tblview.setItems(getArrayOfObjects());

    }

    ObservableList<PaperMasterBean> getArrayOfObjects()
    {

        ObservableList<PaperMasterBean> list= FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from papermaster");
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
                String paper=res.getString("paper");
                String language=res.getString("language");
                String price=String.valueOf(res.getFloat("price"));

                System.out.println(paper+" "+language+" "+price);

                PaperMasterBean pmb=new PaperMasterBean(paper,language,price);
                list.add(pmb);
            }

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return  list;

    }

    Connection con;
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
