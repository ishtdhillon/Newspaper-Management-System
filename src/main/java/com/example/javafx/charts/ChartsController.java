package com.example.javafx.charts;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import com.example.javafx.jdbcc.MysqlDBConnection;

public class ChartsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PieChart chartid;

    @FXML
    private PieChart chartid1;

    void FillChart()
    {

        ObservableList<PieChart.Data> list= FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = con.prepareStatement("select area, COUNT(*) as 'count' from areas group by area");
            ResultSet res= stmt.executeQuery();


            while(res.next())
            {
                String area = res.getString("area");
                int count = res.getInt("count");
                PieChart.Data ref=new PieChart.Data(String.valueOf(area),count);
                list.add(ref);

            }
            chartid.setData(list);
            chartid.setLabelsVisible(false);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }


        ObservableList<PieChart.Data> list1= FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT\n" +
                    "    CASE\n" +
                    "        WHEN price BETWEEN 4 AND 5 THEN '4-5'\n" +
                    "        WHEN price BETWEEN 6 AND 7 THEN '6-7'\n" +
                    "        WHEN price BETWEEN 8 AND 9 THEN '8-9'\n" +
                    "        ELSE 'Other'\n" +
                    "    END AS price_range,\n" +
                    "    COUNT(*) AS total\n" +
                    "FROM papermaster\n" +
                    "GROUP BY price_range;");
            ResultSet res1= stmt.executeQuery();


            while(res1.next())
            {
                String price_range = res1.getString("price_range");
                int total = res1.getInt("total");
                PieChart.Data ref1=new PieChart.Data(String.valueOf(price_range),total);
                list1.add(ref1);

            }
            chartid1.setData(list1);
            chartid1.setLabelsVisible(false);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }

    Connection con;
    @FXML
    void initialize() {
        con= MysqlDBConnection.getMysqlDBConnection();
        if(con==null)
        {
            System.out.println("Connection Error");
            return;
        }

        assert chartid != null : "fx:id=\"chartid\" was not injected: check your FXML file 'ChartsView.fxml'.";
        assert chartid1 != null : "fx:id=\"chartid1\" was not injected: check your FXML file 'ChartsView.fxml'.";

        FillChart();

    }

}
