package com.taskmanager.controller;

import com.taskmanager.TaskManagerApplication;
import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskViewController {

    public ChoiceBox<String> statusChoiceBox;
    public Label dateLabel;
    public Label titleLabel;
    public Button saveButton;
    public Button deleteButton;
    public Button cancelButton;

    private Task task;

    public void initialize(){
        statusChoiceBox.getItems().addAll("To Do", "In Progress", "Done");
    }



    public void setTask(Task task) {
        this.task = task;
        titleLabel.setText(task.getTaskTitle());
        dateLabel.setText("Due: " + task.getTaskDueDate());
        statusChoiceBox.setValue(task.getTaskStatus());
    }

    public void onSaveClick() {
        String newTaskStatus = statusChoiceBox.getValue();
        TaskService.changeTaskStatus(task, newTaskStatus);
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    public void onDeleteClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskManagerApplication.class.getResource("confirm-delete-task.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 250, 200);
        Stage confirmStage = new Stage();
        confirmStage.setTitle("Confirmation");
        confirmStage.setScene(scene);

        com.taskmanager.controller.ConfirmDeleteTaskController controller = fxmlLoader.getController();
        controller.setTask(task);

        controller.setTaskViewStage((Stage) deleteButton.getScene().getWindow());

        confirmStage.show();
    }

    public void onCancelClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
