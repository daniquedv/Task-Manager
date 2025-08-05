package com.taskmanager.controller;

import com.taskmanager.service.TaskListService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmClearTasklistController {
    public Button confirmButton;
    public Button cancelButton;

    @FXML
    public void onConfirmClick(ActionEvent actionEvent) {
        TaskListService.clearTasklist();
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
