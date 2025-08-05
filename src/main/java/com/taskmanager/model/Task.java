package com.taskmanager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Task {

    // Properties of a task object
    String taskTitle;
    UUID taskId;
    String taskStatus;
    LocalDateTime taskCreationDate;
    LocalDate taskDueDate;
    String taskCategory;

    // Getters and setters for the task object properties
    public String getTaskTitle() {
        return taskTitle;
    }
    public UUID getTaskId() {
        return taskId;
    }
    public String getTaskStatus() {
        return taskStatus;
    }
    public LocalDateTime getTaskCreationDate() {
        return taskCreationDate;
    }
    public LocalDate getTaskDueDate() {
        return taskDueDate;
    }
    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
    public void setTaskCreationDate(LocalDateTime taskCreationDate) {
        this.taskCreationDate = taskCreationDate;
    }
    public void setTaskDueDate(LocalDate taskDueDate) {
        this.taskDueDate = taskDueDate;
    }
    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }
}
