package com.teachmeskills.lesson_28;

import com.teachmeskills.lesson_28.repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        userRepository.addUser("Bill", "Gats", 36, "login_bill","pass_bill");
    }
}
