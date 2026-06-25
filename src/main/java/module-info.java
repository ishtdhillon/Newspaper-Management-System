module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.example.javafx to javafx.fxml;
    exports com.example.javafx;

    exports com.example.javafx.jsg;
    opens com.example.javafx.jsg to javafx.fxml;

    exports com.example.javafx.listcombo;
    opens com.example.javafx.listcombo to javafx.fxml;

    exports com.example.javafx.datepicker;
    opens com.example.javafx.datepicker to javafx.fxml;

    exports com.example.javafx.jdbcc;
    opens com.example.javafx.jdbcc to javafx.fxml;

    exports com.example.javafx.papermaster;
    opens com.example.javafx.papermaster to javafx.fxml;

    exports com.example.javafx.hawkers;
    opens com.example.javafx.hawkers to javafx.fxml;

    exports com.example.javafx.areas;
    opens com.example.javafx.areas to javafx.fxml;

    exports com.example.javafx.customers;
    opens com.example.javafx.customers to javafx.fxml;

    exports com.example.javafx.billing;
    opens com.example.javafx.billing to javafx.fxml;

    exports com.example.javafx.tableview;
    opens com.example.javafx.tableview to javafx.fxml;

    exports com.example.javafx.billboard;
    opens com.example.javafx.billboard to javafx.fxml;

    exports com.example.javafx.billcollector;
    opens com.example.javafx.billcollector to javafx.fxml;

    exports com.example.javafx.customerboard;
    opens com.example.javafx.customerboard to javafx.fxml;

    exports com.example.javafx.dashboard;
    opens com.example.javafx.dashboard to javafx.fxml;

    exports com.example.javafx.admin;
    opens com.example.javafx.admin to javafx.fxml;

    exports com.example.javafx.charts;
    opens com.example.javafx.charts to javafx.fxml;

}