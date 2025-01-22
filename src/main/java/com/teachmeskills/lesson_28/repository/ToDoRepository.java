package com.teachmeskills.lesson_28.repository;

import com.teachmeskills.lesson_28.logger.LoggerUtil;

import java.util.*;

public class ToDoRepository {
    private final  Map<String, Set<String>> taskList;
    private final  List<String> tasks = new ArrayList<>();

    public ToDoRepository() {
        taskList = new HashMap<>();
    }

    public  Map<String, Set<String>>  getTaskList(){
        return taskList;
    }

    public Set<String> getTaskByUsername(String username){
        return  taskList.get(username) == null ? new HashSet<>() : taskList.get(username);
    }

}
