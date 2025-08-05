package com.taskmanager.controller;

import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmDeleteTaskController {
    public Button confirmButton;
    public Button cancelButton;

    private Task task;
    private Stage taskViewStage;

    public void setTask(Task task) {
        this.task = task;
    }

    public void setTaskViewStage(Stage stage) {
        this.taskViewStage = stage;
    }

    @FXML
    public void onConfirmClick() {
        TaskService.deleteTask(task);
        Stage confirmStage = (Stage) confirmButton.getScene().getWindow();
        confirmStage.close();
        if (taskViewStage != null) {
            taskViewStage.close();
        }
    }

    @FXML
    public void onCancelClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
