package com.teachmeskills.lesson_28.repository;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static final Map<String, String> users = new HashMap<>();

    static  {
        users.put("admin","111111");
        users.put("user","222222");
    }

    public static boolean isValidLogin(String username, String password){
        if (username == null || password == null){
            return false;
        }
        if (users.containsKey(username)){
            return users.containsValue(password);
        }
        return false;
    }

    public static boolean isAddUser(String username, String password,String confirmPassword){
        if (username == null || password == null || confirmPassword == null){
            return false;
        }
        if (users.containsKey(username) || !password.equals(confirmPassword)){
            return false;
        } else {
            users.put(username, password);
        }
        return  true;
    }

}
