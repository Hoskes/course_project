module com.example.course_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.course_project to javafx.fxml;
    exports com.example.course_project;
}