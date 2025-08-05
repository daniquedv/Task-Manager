package com.taskmanager.controller;

import com.taskmanager.TaskManagerApplication;
import com.taskmanager.model.Task;
import com.taskmanager.service.TaskListService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HomeScreenController {

    public ScrollPane taskListScrollPane;
    public VBox taskListContainer;
    public ChoiceBox<String> statusFilterChoiceBox;
    public ChoiceBox<String> categoryFilterChoiceBox;
    public ChoiceBox<String> sortingChoiceBox;

    @FXML
    public void initialize() {
        // Initial task loading
        loadTasks();

        // Setting up the options and default values for the choice boxes
        statusFilterChoiceBox.getItems().addAll("Show all", "To Do", "In Progress", "Done");
        statusFilterChoiceBox.setValue("Show all");

        categoryFilterChoiceBox.getItems().addAll("Show all", "Household", "Groceries");
        categoryFilterChoiceBox.setValue("Show all");

        sortingChoiceBox.getItems().addAll("Newest first", "Oldest first", "A-Z");
        sortingChoiceBox.setValue("Newest first");
    }

    private void loadTasks() {
        List<Task> tasks = TaskListService.getTaskList();
        taskListContainer.getChildren().clear(); // Clear existing tasks in the container

        if (!tasks.isEmpty()) {
            for (Task task : tasks) {

                if (!statusFilterChoiceBox.getValue().equals("Show All") &&
                    !task.getTaskStatus().equals(statusFilterChoiceBox.getValue())) {
                    continue; // Skip tasks that don't match the selected status filter
                }

                Label taskLabel = new Label(task.getTaskTitle() +
                        "\nDue: \t" + task.getTaskDueDate() +
                        "\nStatus:\t " + task.getTaskStatus() +
                        "\nCategory:\t " + task.getTaskCategory());

                switch (task.getTaskStatus()) {
                    case "To Do" -> taskLabel.setStyle("-fx-background-color: #E66F44; -fx-padding: 10;");
                    case "In Progress" -> taskLabel.setStyle("-fx-background-color: #E6D344; -fx-padding: 10;");
                    case "Done" -> taskLabel.setStyle("-fx-background-color: #72E644; -fx-padding: 10;");
                }

                taskLabel.setMinWidth(557);
                taskLabel.setMaxWidth(557);
                taskLabel.setOnMouseClicked(event -> openTaskView(task));
                taskListContainer.getChildren().add(taskLabel);
            }
        } else {
            Label noTasksLabel = new Label("No tasks available. Click 'Create task' to add a new task.");
            noTasksLabel.setStyle("-fx-padding: 10; -fx-text-fill: #888;");
            taskListContainer.getChildren().add(noTasksLabel);
        }
    }

    @FXML
    private void openTaskView(Task task) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TaskManagerApplication.class.getResource("task-viewer.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 300);
            Stage stage = new Stage();
            stage.setTitle("Task: " + task.getTaskTitle());
            stage.setScene(scene);
            stage.setOnHidden(event -> loadTasks());

            com.taskmanager.controller.TaskViewController controller = fxmlLoader.getController();
            controller.setTask(task);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void onCreateTaskClick() throws IOException {
        // Opens a new window to create a task
        FXMLLoader fxmlLoader = new FXMLLoader(TaskManagerApplication.class.getResource("create-task.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        Stage stage = new Stage();
        stage.setTitle("Create task");
        stage.setScene(scene);
        stage.setOnHidden(event -> loadTasks());
        stage.show();
    }

    @FXML
    protected void onClearTasklistClick(ActionEvent actionEvent) throws IOException {
        if(!TaskListService.getTaskList().isEmpty()) {
            // Opens a new window to create a task
            FXMLLoader fxmlLoader = new FXMLLoader(TaskManagerApplication.class.getResource("confirm-clear-tasklist.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 200);
            Stage taskViewStage = new Stage();
            taskViewStage.setTitle("Confirmation");
            taskViewStage.setScene(scene);
            taskViewStage.setOnHidden(event -> loadTasks());
            taskViewStage.show();
            return;
        }
        else {
            // If the task list is empty, show a message and return
            Stage stage = (Stage) taskListScrollPane.getScene().getWindow();
        }
    }
}
