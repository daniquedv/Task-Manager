module com.taskmanager {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens com.taskmanager to javafx.fxml;
    opens com.taskmanager.controller to javafx.fxml;

    exports com.taskmanager;
    exports com.taskmanager.controller;
}