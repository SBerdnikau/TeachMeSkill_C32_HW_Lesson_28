package com.teachmeskills.lesson_28.repository;

import com.teachmeskills.lesson_28.logger.LoggerUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    //TODO Переписать через проперти  и Синглтон
    final String DB_DRIVER = "org.postgresql.Driver";
    final String DB_URL = "jdbc:postgresql://localhost:5432/tmc_c32_hm_todo";
    final String DB_USER = "postgres";
    final String DB_PASSWORD = "root";

    {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            LoggerUtil.logToFile("Ошибка получения драйвера базы данных " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            return  DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            LoggerUtil.logToFile("Ошибка соединения с базой данных " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
