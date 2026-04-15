module org.example.eventsystemassignment {
    exports org.example.eventsystemassignment.bll;
    exports org.example.eventsystemassignment.be;

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.naming;
    requires javafx.graphics;
    requires javafx.base;


    opens org.example.eventsystemassignment;
    opens org.example.eventsystemassignment.be to javafx.base;
    opens org.example.eventsystemassignment.gui;

}