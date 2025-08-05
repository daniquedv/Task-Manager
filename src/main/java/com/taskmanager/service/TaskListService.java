package com.taskmanager.service;

import com.taskmanager.model.Task;

import java.util.ArrayList;

public class TaskListService {

    private static ArrayList<Task> taskList = new ArrayList<>(); // Create a list of task objects

    public static void addTask(Task task) {
        taskList.add(task);
    }

    public static void deleteTask(Task task) {
        taskList.remove(task);
    }

    public static void clearTasklist() {
        taskList.clear();
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
}
