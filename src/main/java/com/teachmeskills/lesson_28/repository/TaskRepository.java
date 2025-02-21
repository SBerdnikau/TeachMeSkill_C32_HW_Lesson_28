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

    public boolean addTask(User user, String description) {
        Connection connection = databaseService.getConnection();
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.CREATE_TASK);
            preparedStatement.setString(1, description);
            preparedStatement.setLong(2, user.getId());
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
    }


//    public Set<String> getTasksByUsername(String login) {
//        return taskList.get(username) == null ? new HashSet<>() : taskList.get(username);
//    }
}