package com.example.javafx.billboard;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.example.javafx.jdbcc.MysqlDBConnection;
import com.example.javafx.tableview.PaperMasterBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class BillBoardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combobill;

    @FXML
    private DatePicker enddate;

    @FXML
    private DatePicker startdate;

    @FXML
    private TableView<BillBoardBean> tblview;

    @FXML
    void doClear(ActionEvent event)
    {
        enddate.setValue(null);
        startdate.setValue(null);
        combobill.setValue("");
        tblview.setItems(null);

    }

    @FXML
    void doFetch(ActionEvent event)
    {
        TableColumn<BillBoardBean, String> mob=new TableColumn<>("Mobile");
        mob.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        mob.setMinWidth(100);

        TableColumn<BillBoardBean, String> dostart=new TableColumn<>("Date of Start");
        dostart.setCellValueFactory(new PropertyValueFactory<>("dos"));
        dostart.setMinWidth(100);

        TableColumn<BillBoardBean, String> doend=new TableColumn<>("Date of End");
        doend.setCellValueFactory(new PropertyValueFactory<>("doe"));
        doend.setMinWidth(100);

        TableColumn<BillBoardBean, String> exdays=new TableColumn<>("Excluded Days");
        exdays.setCellValueFactory(new PropertyValueFactory<>("days"));
        exdays.setMinWidth(100);

        TableColumn<BillBoardBean, String> bil=new TableColumn<>("Bill");
        bil.setCellValueFactory(new PropertyValueFactory<>("bill"));
        bil.setMinWidth(100);

        TableColumn<BillBoardBean, String> stat=new TableColumn<>("Status");
        stat.setCellValueFactory(new PropertyValueFactory<>("status"));
        stat.setMinWidth(100);

        if (tblview.getColumns().isEmpty())
        {
            tblview.getColumns().addAll(mob,dostart,doend,exdays,bil,stat);
        }

        tblview.setItems(null);
        tblview.setItems(getArrayOfObjects());


    }

    ObservableList<BillBoardBean> getArrayOfObjects()
    {

        ObservableList<BillBoardBean> list= FXCollections.observableArrayList();


        try
        {
            LocalDate dts=startdate.getValue();
            Date stdt= Date.valueOf(dts);

            LocalDate dte=enddate.getValue();
            Date endt= Date.valueOf(dte);

            PreparedStatement stmt;
            ResultSet res;
            String query = "";
            
            if(combobill.getValue().equals("All Bills"))
                query="select * from billing where dos>=? and doe<=?";
            else if(combobill.getValue().equals("Paid Bills"))
                query="select * from billing where dos>=? and doe<=? and status=0";
            else if(combobill.getValue().equals("Unpaid Bills"))
                query="select * from billing where dos>=? and doe<=? and status=1";
            
            stmt=con.prepareStatement(query);
            stmt.setDate(1,stdt);
            stmt.setDate(2,endt);
            res=stmt.executeQuery();


            while(res.next())
            {
                String mobile=res.getString("mobile");
                String dos=String.valueOf(res.getDate("dos"));
                String doe=String.valueOf(res.getDate("doe"));
                String days=res.getString("days");
                String bill=res.getString("bill");
                String status=String.valueOf(res.getInt("status"));

                System.out.println(mobile+" "+dos+" "+doe+" "+days+" "+bill+" "+status);

                BillBoardBean bbb=new BillBoardBean(mobile,dos,doe,days,bill,status);
                list.add(bbb);
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
        combobill.getItems().add("select");
        combobill.getItems().add("All Bills");
        combobill.getItems().add("Paid Bills");
        combobill.getItems().add("Unpaid Bills");
        combobill.getSelectionModel().select(0);
        
        con = MysqlDBConnection.getMysqlDBConnection();
        if (con == null)
        {
            System.out.println("Connection Error");
            return;
        }

        /*java.time.LocalDate dos = startdate.getValue();
        java.time.LocalDate doe = enddate.getValue();
        java.sql.Date sqlDos = java.sql.Date.valueOf(dos);
        java.sql.Date sqlDoe = java.sql.Date.valueOf(doe);*/


    }

}
