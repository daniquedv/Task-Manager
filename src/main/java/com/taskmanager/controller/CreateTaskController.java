package com.taskmanager.controller;

import com.taskmanager.service.TaskService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class CreateTaskController {

    @FXML
    public TextField taskTitleField;

    @FXML
    public DatePicker dueDatePicker;

    @FXML
    public Button createButton;

    @FXML
    public Button cancelButton;

    @FXML
    public ChoiceBox<String> statusChoiceBox;
    public ChoiceBox<String> categoryChoiceBox;

    public void initialize(){
        statusChoiceBox.getItems().addAll("To Do", "In Progress", "Done");
        statusChoiceBox.setValue("To Do");

        categoryChoiceBox.getItems().addAll("No category", "Household", "Groceries");
        categoryChoiceBox.setValue("No category");

        dueDatePicker.setValue(LocalDate.now());
    }

    // Handle create button click
    @FXML
    public void onCreateClick() {
        String taskTitle = taskTitleField.getText();

        if (taskTitle == null || taskTitle.trim().isEmpty()) {
            // Show an alert to the user
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Title Error");
            alert.setHeaderText(null);
            alert.setContentText("Task title cannot be empty.");
            alert.showAndWait();
            return;
        }

        LocalDate taskDueDate = dueDatePicker.getValue();
        String taskStatus = statusChoiceBox.getValue();
        String taskCategory = categoryChoiceBox.getValue();

        TaskService.createTask(taskTitle, taskDueDate, taskStatus, taskCategory);

        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
    }

    // Handle cancel button click
    @FXML
    public void onCancelClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
