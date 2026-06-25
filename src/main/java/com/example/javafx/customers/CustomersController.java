package com.example.javafx.customers;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.javafx.jdbcc.MysqlDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class CustomersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboar;

    @FXML
    private ComboBox<String> combohid;

    @FXML
    private DatePicker dos;

    @FXML
    private ListView<String> listPaper;

    @FXML
    private ListView<String> listPrice;

    @FXML
    private ListView<String> listSelPaper;

    @FXML
    private ListView<String> listSelPrice;

    @FXML
    private TextField txtaddr;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtmob;

    @FXML
    private TextField txtname;

    @FXML
    void doadd(MouseEvent event)
    {
        if (event.getClickCount() == 2)
        {
            String selitem = listPaper.getSelectionModel().getSelectedItem();
            listSelPaper.getItems().add(selitem);

            int ind = listPaper.getSelectionModel().getSelectedIndex();
            listPrice.getSelectionModel().select(ind);

            listSelPrice.getItems().add(listPrice.getSelectionModel().getSelectedItem());

        }

    }

    @FXML
    void doDelete(MouseEvent event)
    {
        if (event.getClickCount() == 2)
        {
            listSelPaper.getItems().remove(listSelPaper.getSelectionModel().getSelectedIndex());
            int ind = listPaper.getSelectionModel().getSelectedIndex();
            listPrice.getSelectionModel().select(ind);
            listSelPrice.getItems().remove(listPrice.getSelectionModel().getSelectedIndex());

        }

    }


    @FXML
    void CreateNew(ActionEvent event)
    {
        txtname.setText("");
        txtemail.setText("");
        txtmob.setText("");
        txtaddr.setText("");
        dos.setValue(null);
        comboar.setValue("");
        combohid.setValue("");
        listSelPaper.getItems().clear();
        listSelPrice.getItems().clear();

    }

    @FXML
    void doEdit(ActionEvent event)
    {
        String query = "update customers set cname=?, emailid=?, dos=?, address=?, area=?, hawkerid=? where mobile=?";
        try
        {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, txtname.getText());
            pst.setString(2, txtemail.getText());

            LocalDate lcl = dos.getValue();
            java.sql.Date dt = java.sql.Date.valueOf(lcl);
            pst.setDate(3, dt);

            pst.setString(4, txtaddr.getText());
            pst.setString(5, comboar.getValue());
            pst.setString(6, combohid.getValue());
            pst.setString(7, txtmob.getText());


            int count = pst.executeUpdate();
            if (count == 0)
                System.out.println("Invalid ID");
            else
                System.out.println("Record Modified");

        }
        catch (Exception exp)
        {
            System.out.println(exp);
        }


    }

    Connection con;

    @FXML
    void doEnroll(ActionEvent event)
    {
        String query = "insert into customers values(?,?,?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, txtmob.getText());
            pst.setString(2, txtname.getText());
            pst.setString(3, txtemail.getText());

            LocalDate lcl = dos.getValue();
            java.sql.Date dt = java.sql.Date.valueOf(lcl);
            pst.setDate(4, dt);

            pst.setString(5, txtaddr.getText());
            pst.setString(6, comboar.getValue());
            pst.setString(7, combohid.getValue());

            ObservableList<String> selectedPapers = listSelPaper.getItems();
            ObservableList<String> selectedPrices = listSelPrice.getItems();
            String listpap = String.join(",", selectedPapers);
            String listprc = String.join(",", selectedPrices);

            pst.setString(8, listpap);
            pst.setString(9, listprc);

            pst.executeUpdate();
            System.out.println("Customer Enrolled");

        }
        catch (Exception exp)
        {
            System.out.println(exp);
        }

    }

    @FXML
    void doFind(ActionEvent event)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("select * from customers where mobile=?");
            stmt.setString(1, txtmob.getText());
            ResultSet res = stmt.executeQuery();

            if (res.next() == true)
            {

                String name = res.getString("cname");
                String email = res.getString("emailid");
                Date dt = res.getDate("dos");
                String address = res.getString("address");


                txtname.setText(name);
                txtemail.setText(email);
                dos.setValue(dt.toLocalDate());
                txtaddr.setText(address);

                comboar.setValue(res.getString("area"));
                combohid.setValue(res.getString("hawkerid"));

                /*String area = res.getString("area");
                comboar.getSelectionModel().select(area);

                String hawkerId = res.getString("hawkerid");
                combohid.getSelectionModel().select(hawkerId);*/

                String spap=res.getString("papers");
                String[] ary=spap.split(",");
                for(String s : ary)
                {
                    listSelPaper.getItems().add(s);
                }

                String sprc=res.getString("prices");
                String[] aryy=spap.split(",");
                for(String ss : aryy)
                {
                    listSelPrice.getItems().add(ss);
                }

            }
            else
            {
                System.out.println("Invalid ID");
            }

        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

    }


    @FXML
    void doRemove(ActionEvent event)
    {
        String query = "delete from customers where mobile=?";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, txtmob.getText());

            int count = pst.executeUpdate();
            if (count == 0)
                System.out.println("Invalid ID");
            else
                System.out.println("Customer Removed");

        }
        catch (Exception exp)
        {
            System.out.println(exp);
        }


    }

    @FXML
    void doFillHawkerid(ActionEvent event)
    {
        try {
            String query = "select hawkerid from hawkers where selareas like ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,"%"+comboar.getValue()+"%");
            ResultSet res=pst.executeQuery();
            while(res.next())
            {
                String id=res.getString("hawkerid");
                combohid.getItems().add(id);
            }
        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }

    }

    ArrayList<String> getAllAreas()
    {
        ArrayList<String> Areas = new ArrayList<String>();

        try {
            PreparedStatement stmt = con.prepareStatement("select area from areas");

            ResultSet res = stmt.executeQuery();

            while (res.next())
            {
                String area = res.getString("area");
                Areas.add(area);

            }
            System.out.println(Areas);


        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
        return Areas;
    }



    ArrayList<String> showpapers()
    {
        ArrayList<String> pap = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement("select distinct paper from papermaster");

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                String paper = res.getString("paper");
                pap.add(paper);
            }
            System.out.println(pap);

        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

        return pap;
    }

    ArrayList<String> showprices()
    {
        ArrayList<String> prc = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement("select price from papermaster");

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                String pri = res.getString("price");
                prc.add(pri);
            }
            System.out.println(prc);
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

        return prc;
    }

    @FXML
    void initialize() {
        con = MysqlDBConnection.getMysqlDBConnection();
        if (con == null) {
            System.out.println("Connection Error");
            return;
        }

        combohid.getItems().add(null);

        ArrayList<String> lstAreas = getAllAreas();
        for (String str : lstAreas) {
            comboar.getItems().add(str);
        }


        ArrayList<String> lstpaper = showpapers();
        for (String s : lstpaper) {
            listPaper.getItems().add(s);
        }

        ArrayList<String> lstprice = showprices();
        for (String s : lstprice) {
            listPrice.getItems().add(s);
        }
    }
}

