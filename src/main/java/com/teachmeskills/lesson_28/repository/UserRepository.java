package com.teachmeskills.lesson_28.repository;

import com.teachmeskills.lesson_28.logger.LoggerUtil;
import com.teachmeskills.lesson_28.model.User;
import com.teachmeskills.lesson_28.util.SQLQuery;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserRepository {

    DatabaseService databaseService;

    public UserRepository() {
        databaseService = new DatabaseService();
    }

    public Set<User> getAllUsers(){
        Connection connection = databaseService.getConnection();
        PreparedStatement preparedStatement;
        Set<User> users = new HashSet<>();
        try {
            preparedStatement = connection.prepareStatement(SQLQuery.GET_ALL_USERS);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                User user = parseUser(result);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public boolean isValid(String username, String password)  {
        Connection connection = databaseService.getConnection();
        PreparedStatement preparedStatement;

        User user = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQuery.IS_VALID);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

           ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                user = parseUser(result);
            }

            if (user == null || user.getFirstName() == null || user.getSecondName() == null){
                return false;
            }

            if (user.getFirstName().equals(username)  ){
                return user.getSecondName().equals(password);
            }

            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isContainsUserByName(String username){
        Set<User> users = getAllUsers();
        for (User user : users){
            if(user.getFirstName().equals(username)){
                return true;
            }
        }
        return false;
    }

    public User parseUser(ResultSet result) {
        try {
            User user = new User();
            user.setId(result.getLong("id"));
            user.setFirstName(result.getString("firstname"));
            user.setSecondName(result.getString("secondname"));
            user.setCreated(result.getDate("created"));
            user.setChanged(result.getDate("changed"));
            user.setAge(result.getInt("age"));
            return user;
        }catch (SQLException e) {
            LoggerUtil.logToFile("parse user" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean addUser(String firstName, String secondName, int age, String login, String password)  {
        Connection connection = databaseService.getConnection();

        try {
            //1.Добавляем пользователя в базу с транзакцией
            //начала транзакции (без коммита устаноить)
            connection.setAutoCommit(false);
            PreparedStatement preparedUserStatement = connection.prepareStatement(SQLQuery.CREATE_USER, Statement.RETURN_GENERATED_KEYS);//генерируем сами Id для users в транзакции
            preparedUserStatement.setString(1, firstName);
            preparedUserStatement.setString(2, secondName);
            preparedUserStatement.setInt(3, age);
            preparedUserStatement.executeUpdate();

            ResultSet generatedKeys = preparedUserStatement.getGeneratedKeys();//Вернули ключи результатоа созданых строк(виртуально в памяти)
            long userID = -1; //если быдет ошибка при добавлении новой записи база вызвст ошибку на -1 в id

            if (generatedKeys.next()) {
                //забираем Id
                userID = generatedKeys.getLong(1);
            }

            //2.Добавляем Security даные таблицы в транзакции
            PreparedStatement preparedSecurityStatement = connection.prepareStatement(SQLQuery.CREATE_SECURITY);//ключи Statement.RETURN_GENERATED_KEYS можно не указывать(не главная таблица)
            preparedSecurityStatement.setString(1, login);
            preparedSecurityStatement.setString(2, password);
            preparedSecurityStatement.setLong(3, userID);
            preparedSecurityStatement.executeUpdate();

            //если всё хорошо то делаем коммит в базу
            connection.commit();
            LoggerUtil.logToFile("user successfully added to the database");
            return true;

        }catch (Exception e) {
            LoggerUtil.logToFile("Exception add user" + e.getMessage());
            //откатить изменения
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback exception" + ex.getMessage());
            }
        }
        return false;
    }
}
