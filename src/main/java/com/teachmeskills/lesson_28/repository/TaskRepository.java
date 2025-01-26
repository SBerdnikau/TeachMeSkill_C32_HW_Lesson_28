package com.teachmeskills.lesson_28.repository;

import java.util.*;

public class TaskRepository {
    private final  Map<String, Set<String>> taskList;

    public TaskRepository() {
        taskList = new HashMap<>();
    }

    public Map<String, Set<String>> getTaskList() {
        return taskList;
    }

    public Set<String> getTasksByUsername(String username) {
        return taskList.get(username) == null ? new HashSet<>() : taskList.get(username);
    }
}