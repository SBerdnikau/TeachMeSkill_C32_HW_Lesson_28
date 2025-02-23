package com.teachmeskills.lesson_28.util;

public interface SQLQuery {
    String GET_ALL_USERS = "SELECT * FROM users";
    String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    String CREATE_SECURITY = "INSERT INTO security (id, login, password, user_id) VALUES (DEFAULT, ?, ?, ?)";
    String CREATE_USER = "INSERT INTO users (id, first_name, second_name, created, changed, age) VALUES (DEFAULT, ?, ?, DEFAULT, DEFAULT, ?)";
    String IS_VALID = "SELECT * FROM users WHERE id = (SELECT user_id FROM security WHERE login  = ? AND password  = ?)";
    String CALL_DELETE_USER_BY_FIRSTNAME = "CALL delete_user_by_first_name('Bill333')";
    String GET_SECURITY_BY_LOGIN = "SELECT * FROM security WHERE login = ?";
    String CREATE_TASK = "INSERT INTO tasks (id, description, created, changed, user_id) VALUES (DEFAULT, ?, DEFAULT, DEFAULT, ?)";
    String GET_TASK_BY_LOGIN = "SELECT * FROM tasks WHERE user_id = (SELECT id FROM users WHERE id = (SELECT id FROM security WHERE login = ?));";
    String GET_ALL_TASKS = "SELECT * FROM tasks";
    String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE id = (SELECT user_id FROM security WHERE login  = ?)";;
    String DELETE_TASK_BY_LOGIN = "DELETE FROM tasks WHERE user_id = (SELECT user_id FROM security WHERE login = ?) AND description = ?";
}
