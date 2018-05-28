package com.example.a16022895.taskmanager;

public class Task {

    private int id;
    private String taskName;
    private String taskDetail;

    public Task(int id, String taskName, String taskDetail) {
        this.id = id;
        this.taskName = taskName;
        this.taskDetail = taskDetail;
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDetail() {
        return taskDetail;
    }
}
