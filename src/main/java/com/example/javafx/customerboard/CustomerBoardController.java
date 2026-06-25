package com.example.javafx.customerboard;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.javafx.jdbcc.MysqlDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerBoardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboarea;

    @FXML
    private ComboBox<String> combohawker;

    @FXML
    private ComboBox<String> combopaper;

    @FXML
    private TableView<CustomerBoardBean> tblview;

    @FXML
    void doClear(ActionEvent event)
    {
        combohawker.setValue("");
        combopaper.setValue("");
        comboarea.setValue("");
        tblview.setItems(null);

    }

    @FXML
    void doShow(ActionEvent event)
    {
        TableColumn<CustomerBoardBean,String> mob=new TableColumn<CustomerBoardBean,String>("Mobile No");
        mob.setCellValueFactory(new PropertyValueFactory<CustomerBoardBean,String>("mobile"));
        mob.setMinWidth(100);

        TableColumn<CustomerBoardBean,String> nameC=new TableColumn<CustomerBoardBean,String>("Name");
        nameC.setCellValueFactory(new PropertyValueFactory<CustomerBoardBean,String>("cname"));
        nameC.setMinWidth(100);

        TableColumn<CustomerBoardBean,String> mail=new TableColumn<CustomerBoardBean,String>("E-Mail");
        mail.setCellValueFactory(new PropertyValueFactory<CustomerBoardBean,String>("emailid"));
        mail.setMinWidth(100);

        TableColumn<CustomerBoardBean,String> dos=new TableColumn<CustomerBoardBean,String>("Start Date");
        dos.setCellValueFactory(new PropertyValueFactory<CustomerBoardBean,String>("dos"));
        dos.setMinWidth(100);

        TableColumn<CustomerBoardBean,String> area=new TableColumn<CustomerBoardBean,String>("Area");
        area.setCellValueFactory(new PropertyValueFactory<CustomerBoardBean,String>("area"));
        area.setMinWidth(100);

        TableColumn<CustomerBoardBean,String> hid=new TableColumn<CustomerBoardBean,String>("Hawker");
        hid.setCellValueFactory(new PropertyValueFactory<CustomerBoardBean,String>("hawkerid"));
        hid.setMinWidth(100);

        TableColumn<CustomerBoardBean,String> paper=new TableColumn<CustomerBoardBean,String>("Papers");
        paper.setCellValueFactory(new PropertyValueFactory<CustomerBoardBean,String>("papers"));
        paper.setMinWidth(100);

        TableColumn<CustomerBoardBean,String> price=new TableColumn<CustomerBoardBean,String>("Prices");
        price.setCellValueFactory(new PropertyValueFactory<CustomerBoardBean,String>("prices"));
        price.setMinWidth(100);

        if (tblview.getColumns().isEmpty())
        {
            tblview.getColumns().addAll(mob,nameC,mail,dos,area,hid,paper,price);
        }

        tblview.setItems(null);
        tblview.setItems(getArrayOfObjects());

    }

    ObservableList<CustomerBoardBean> getArrayOfObjects()
    {
        ObservableList<CustomerBoardBean> list = FXCollections.observableArrayList();
        PreparedStatement pst;
        ResultSet rs;

        try {
            if (comboarea.getValue().equals("None") && combohawker.getValue().equals("None") && combopaper.getValue().equals("None")) {
                pst = con.prepareStatement("SELECT mobile, cname, emailid, dos, area, hawkerid, papers, prices FROM customers");
            } else if (!comboarea.getValue().equals("None") && combohawker.getValue().equals("None") && combopaper.getValue().equals("None")) {
                pst = con.prepareStatement("SELECT mobile, cname, emailid, dos, area, hawkerid, papers, prices FROM customers WHERE area = ?");
                pst.setString(1, comboarea.getValue());
            } else if (comboarea.getValue().equals("None") && !combohawker.getValue().equals("None") && combopaper.getValue().equals("None")) {
                pst = con.prepareStatement("SELECT mobile, cname, emailid, dos, area, hawkerid, papers, prices FROM customers WHERE hawkerid = ?");
                pst.setString(1, combohawker.getValue());
            } else if (comboarea.getValue().equals("None") && combohawker.getValue().equals("None") && !combopaper.getValue().equals("None")) {
                pst = con.prepareStatement("SELECT mobile, cname, emailid, dos, area, hawkerid, papers, prices FROM customers WHERE papers Like ?");
                pst.setString(1, "%"+combopaper.getValue()+"%");
            } else if (!comboarea.getValue().equals("None") && !combohawker.getValue().equals("None") && combopaper.getValue().equals("None")) {
                pst = con.prepareStatement("SELECT mobile, cname, emailid, dos, area, hawkerid, papers, prices FROM customers WHERE area = ? AND hawkerid = ?");
                pst.setString(1, comboarea.getValue());
                pst.setString(2, combohawker.getValue());
            } else if (comboarea.getValue().equals("None") && !combohawker.getValue().equals("None") && !combopaper.getValue().equals("None")) {
                pst = con.prepareStatement("SELECT mobile, cname, emailid, dos, area, hawkerid, papers, prices FROM customers WHERE hawkerid = ? AND papers Like ?");
                pst.setString(1, combohawker.getValue());
                pst.setString(2, "%"+combopaper.getValue()+"%");
            } else if (!comboarea.getValue().equals("None") && combohawker.getValue().equals("None") && !combopaper.getValue().equals("None")) {
                pst = con.prepareStatement("SELECT mobile, cname, emailid, dos, area, hawkerid, papers, prices FROM customers WHERE area = ? AND papers Like ?");
                pst.setString(1, comboarea.getValue());
                pst.setString(2, "%"+combopaper.getValue()+"%");
            } else {
                pst = con.prepareStatement("SELECT mobile, cname, emailid, dos, area, hawkerid, papers, prices FROM customers WHERE area = ? AND hawkerid = ? AND papers Like ?");
                pst.setString(1, comboarea.getValue());
                pst.setString(2, combohawker.getValue());
                pst.setString(3, "%"+combopaper.getValue()+"%");
            }

            rs = pst.executeQuery();

            while (rs.next()) {
                String mob = rs.getString("mobile");
                String name = rs.getString("cname");
                String email = rs.getString("emailid");
                Date dos = rs.getDate("dos");
                String area = rs.getString("area");
                String hawker = rs.getString("hawkerid");
                String paper = rs.getString("papers");
                String price = rs.getString("prices");

                CustomerBoardBean bean = new CustomerBoardBean(mob, name, email, dos.toString(), area, hawker, paper, price);
                list.add(bean);
            }

        } catch (Exception exp) {
            exp.printStackTrace();
        }

        return list;
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

    ArrayList<String> showhawkerid()
    {
        ArrayList<String> hid = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement("select distinct hawkerid from hawkers");

            ResultSet res = stmt.executeQuery();

            while (res.next())
            {
                String paper = res.getString("hawkerid");
                hid.add(paper);
            }
            System.out.println(hid);

        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

        return hid;
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

        comboarea.getItems().add("None");
        ArrayList<String> lstAreas = getAllAreas();
        for (String str : lstAreas) {
            comboarea.getItems().add(str);
        }

        combopaper.getItems().add("None");
        ArrayList<String> lstpaper = showpapers();
        for (String s : lstpaper) {
            combopaper.getItems().add(s);
        }

        combohawker.getItems().add("None");
        ArrayList<String> lsthid = showhawkerid();
        for(String strhid : lsthid)
        {
            combohawker.getItems().add(strhid);
        }


    }

}
