package com.teachmeskills.lesson_28.repository;

import com.teachmeskills.lesson_28.logger.LoggerUtil;
import com.teachmeskills.lesson_28.model.Task;
import com.teachmeskills.lesson_28.model.User;
import com.teachmeskills.lesson_28.util.SQLQuery;

import java.sql.*;
import java.util.*;

public class TaskRepository {

    DatabaseService databaseService;

    public TaskRepository() {
        databaseService = new DatabaseService();
    }

    public Boolean addTask(String login, String taskDescription) {
        Connection connection = databaseService.getConnection();
        PreparedStatement preparedStatement;
        UserRepository userRepository = new UserRepository();
        try {
            connection.setAutoCommit(false);
            //1й этап создать задание
            User user = userRepository.getUserByLogin(login);
            preparedStatement = connection.prepareStatement(SQLQuery.CREATE_TASK);
            preparedStatement.setString(1, taskDescription);
            preparedStatement.setLong(2,   user.getId() );
            preparedStatement.executeUpdate();
            connection.commit();
            LoggerUtil.logToFile("task successfully added to the database");
            return true;
        }catch (SQLException e){
            LoggerUtil.logToFile("Exception add task" + e.getMessage());
            try {
                connection.rollback();
            }catch (SQLException ex){
                LoggerUtil.logToFile("Rollback exception" + ex.getMessage());
            }
        }

        return false;
    }

    public Set<String> getTaskByLogin(String login) {
        Connection connection = databaseService.getConnection();
        PreparedStatement preparedStatement;
        Set<String> tasks = new HashSet<>();
        try {
            preparedStatement = connection.prepareStatement(SQLQuery.GET_TASK_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = parseTask(resultSet);
                tasks.add(task.getDescription());
            }
        }catch (SQLException e){
            LoggerUtil.logToFile("Exception getting tasks by login" + e.getMessage());
        }
        return tasks;
    }

    public Task parseTask(ResultSet resultSet) {
        try {
            Task task = new Task();
            task.setId(resultSet.getLong("id"));
            task.setDescription(resultSet.getString("description"));
            task.setCreated(resultSet.getDate("created"));
            task.setChanged(resultSet.getDate("changed"));
            task.setIdUserFk(resultSet.getInt("user_id"));
            return task;
        }catch (SQLException e){
            LoggerUtil.logToFile("Exception parsing task" + e.getMessage());
        }
        return null;
    }


    public Set<Task> getAllTasks() {
        Connection connection = databaseService.getConnection();
        PreparedStatement preparedStatement;
        Set<Task> tasks = new HashSet<>();
        try {
            preparedStatement = connection.prepareStatement(SQLQuery.GET_ALL_TASKS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = parseTask(resultSet);
                tasks.add(task);
            }
        }catch (SQLException e){
            LoggerUtil.logToFile("Exception getting tasks" + e.getMessage());
        }
        return tasks;
    }

    public Boolean removeTaskByLogin(String login, String deleteTask) {
        Set<String> tasks = new HashSet<>();
        Connection connection = databaseService.getConnection();
        PreparedStatement preparedStatement;
        UserRepository userRepository = new UserRepository();
        try{
            connection.setAutoCommit(false);
            User user = userRepository.getUserByLogin(login);
            preparedStatement = connection.prepareStatement(SQLQuery.DELETE_TASK_BY_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, deleteTask);
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        }catch (SQLException e){
            LoggerUtil.logToFile("Exception removing task" + e.getMessage());
        }
        return false;
    }
}