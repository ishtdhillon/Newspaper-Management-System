package com.example.javafx.billcollector;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.example.javafx.billcollector.BillCollectorBean;
import com.example.javafx.jdbcc.MysqlDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BillCollectorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<BillCollectorBean> tblview;

    @FXML
    private TextField txtamt;

    @FXML
    private TextField txtmob;

    @FXML
    void doClear(ActionEvent event)
    {
        txtmob.setText("");
        txtamt.setText("");
        tblview.setItems(null);

    }

    @FXML
    void doPaid(ActionEvent event)
    {
        String query = "update billing set status=0 where mobile=?";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, txtmob.getText());

            int count = pst.executeUpdate();
            if (count == 0)
                System.out.println("Invalid Mobile Number");
            else
                System.out.println("Bill Status Updated");

        }
        catch (Exception exp)
        {
            System.out.println(exp);
        }

    }

    @FXML
    void showBill(ActionEvent event)
    {
        TableColumn<BillCollectorBean, String> mob=new TableColumn<>("Mobile");
        mob.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        mob.setMinWidth(100);

        TableColumn<BillCollectorBean, String> dostart=new TableColumn<>("Date of Start");
        dostart.setCellValueFactory(new PropertyValueFactory<>("dos"));
        dostart.setMinWidth(100);

        TableColumn<BillCollectorBean, String> doend=new TableColumn<>("Date of End");
        doend.setCellValueFactory(new PropertyValueFactory<>("doe"));
        doend.setMinWidth(100);

        TableColumn<BillCollectorBean, String> exdays=new TableColumn<>("Excluded Days");
        exdays.setCellValueFactory(new PropertyValueFactory<>("days"));
        exdays.setMinWidth(100);

        TableColumn<BillCollectorBean, String> bil=new TableColumn<>("Bill");
        bil.setCellValueFactory(new PropertyValueFactory<>("bill"));
        bil.setMinWidth(100);

        TableColumn<BillCollectorBean, String> stat=new TableColumn<>("Status");
        stat.setCellValueFactory(new PropertyValueFactory<>("status"));
        stat.setMinWidth(100);

        if (tblview.getColumns().isEmpty())
        {
            tblview.getColumns().addAll(mob,dostart,doend,exdays,bil,stat);
        }

        tblview.setItems(null);
        tblview.setItems(getArrayOfObjects());


        try
        {
            PreparedStatement stmt = stmt = con.prepareStatement("select bill from billing where mobile=?");
            stmt.setString(1, txtmob.getText());
            ResultSet res = res = stmt.executeQuery();

            if (res.next() == true)
            {

                float total = 0;
                String bill = res.getString("bill");
                for (String p : bill.split(","))
                {
                    total += Float.parseFloat(p);
                }
                txtamt.setText(String.valueOf(total));
            }
            else
            {
                System.out.println("Invalid Mobile Number");
            }
        }
        catch(Exception exp)
        {

        }
    }

    ObservableList<BillCollectorBean> getArrayOfObjects()
    {

        ObservableList<BillCollectorBean> list= FXCollections.observableArrayList();


        try
        {
            PreparedStatement stmt=con.prepareStatement("select * from billing where mobile=?");
            stmt.setString(1, txtmob.getText());
            ResultSet res=stmt.executeQuery();

            while(res.next())
            {
                String mobile=res.getString("mobile");
                String dos=String.valueOf(res.getDate("dos"));
                String doe=String.valueOf(res.getDate("doe"));
                String days=res.getString("days");
                String bill=res.getString("bill");
                String status=String.valueOf(res.getInt("status"));

                System.out.println(mobile+" "+dos+" "+doe+" "+days+" "+bill+" "+status);

                BillCollectorBean bcb=new BillCollectorBean(mobile,dos,doe,days,bill,status);
                list.add(bcb);
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
        if (con == null)
        {
            System.out.println("Connection Error");
            return;
        }
    }

}
