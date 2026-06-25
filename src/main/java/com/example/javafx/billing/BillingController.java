package com.example.javafx.billing;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.example.javafx.jdbcc.MysqlDBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class BillingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtbill;

    @FXML
    private TextField txtdstart;

    @FXML
    private DatePicker txtdup;

    @FXML
    private TextField txtexc;

    @FXML
    private TextField txtmob;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txttotprc;

    @FXML
    void doClear(ActionEvent event)
    {
        txtname.setText("");
        txtmob.setText("");
        txttotprc.setText("");
        txtdstart.setText("");
        txtdup.setValue(null);
        txtexc.setText("");
        txtbill.setText("");

    }


    @FXML
    void doBilling(ActionEvent event)
    {
        try {
            java.time.LocalDate dos = java.time.LocalDate.parse(txtdstart.getText());
            java.time.LocalDate doe = txtdup.getValue();

            int totalDays = (int) java.time.temporal.ChronoUnit.DAYS.between(dos, doe);
            int  lessDays = Integer.parseInt(txtexc.getText());

            int billableDays = totalDays - lessDays;

            txtexc.setText(String.valueOf(lessDays));

            float dailyPrice = Float.parseFloat(txttotprc.getText());
            float bill = billableDays * dailyPrice;

            txtbill.setText(String.valueOf(bill));

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }

    }

    @FXML
    void doFetch(ActionEvent event)
    {

        try
        {
            PreparedStatement stmt = con.prepareStatement("select cname,dos,prices from customers where mobile=?");
            stmt.setString(1, txtmob.getText());
            ResultSet res = stmt.executeQuery();

            if (res.next() == true)
            {
                String name = res.getString("cname");
                Date dts = res.getDate("dos");

                txtname.setText(name);
                txtdstart.setText(String.valueOf(dts.toLocalDate()));

                float total=0;
                String prices = res.getString("prices");
                for (String p : prices.split(","))
                {
                    total += Float.parseFloat(p);
                }
                txttotprc.setText(String.valueOf(total));


            }
            else
            {
                System.out.println("Invalid Mobile Number");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void doDelete(ActionEvent event)
    {
        String query = "delete from billing where mobile=?";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, txtmob.getText());

            int count = pst.executeUpdate();
            if (count == 0)
                System.out.println("Invalid Mobile Number");
            else
                System.out.println("Bill Deleted");

        }
        catch (Exception exp)
        {
            System.out.println(exp);
        }

    }

    Connection con;
    @FXML
    void doSave(ActionEvent event)
    {
        try {
            String mobile = txtmob.getText();
            String dos = txtdstart.getText();
            java.time.LocalDate doe = txtdup.getValue();
            String bill = txtbill.getText();
            String excdays = txtexc.getText();

            java.sql.Date sqlDos = java.sql.Date.valueOf(dos);
            java.sql.Date sqlDoe = java.sql.Date.valueOf(doe);

            int days = 0;
            try
            {
                days = Integer.parseInt(excdays);
            }
            catch (NumberFormatException e)
            {
                txtexc.setText("0");
                days = 0;
            }

            String query = "INSERT INTO billing (mobile, dos, doe, days, bill, status) VALUES (?, ?, ?, ?, ?, 1)";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, mobile);
            pst.setDate(2, sqlDos);
            pst.setDate(3, sqlDoe);
            pst.setInt(4, days);
            pst.setString(5, bill);

           pst.executeUpdate();
           System.out.println("Bill Saved");

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }

    }

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
