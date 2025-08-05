package com.taskmanager.service;

import com.taskmanager.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class TaskService {

    // Create task using taskTitle and taskDueDate from user input
    public static void createTask(String taskTitle, LocalDate taskDueDate, String taskStatus, String taskCategory) {
        Task task = new Task();                         // Create a new task object

        task.setTaskTitle(taskTitle);                   // Set task title with user input
        task.setTaskId(UUID.randomUUID());              // Set task id
        task.setTaskStatus(taskStatus);                    // Set default task status
        task.setTaskCategory(taskCategory);
        task.setTaskCreationDate(LocalDateTime.now());  // Set creation at now
        task.setTaskDueDate(taskDueDate);               // Set task due date with user input

        TaskListService.addTask(task);
    }

    // Delete task from tasklist using TaskListService
    public static void deleteTask(Task task) {
        TaskListService.deleteTask(task);
    }

    // Change task status
    public static void changeTaskStatus(Task task, String newTaskStatus) {
        task.setTaskStatus(newTaskStatus);
    }

}
