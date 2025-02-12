package com.teachmeskills.lesson_28.util;

public interface SQLQuery {
    String GET_ALL_USERS = "SELECT * FROM users";
    String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    String CREATE_SECURITY = "INSERT INTO security (id, login, password, id_user_fk) VALUES (DEFAULT, ?, ?, ?)";
    String CREATE_USER = "INSERT INTO users (id, firstname, secondname, created, changed, age) VALUES (DEFAULT, ?, ?, DEFAULT, DEFAULT, ?)";
    String IS_VALID = "SELECT * FROM users WHERE firstname = ? AND password = ?";
}
