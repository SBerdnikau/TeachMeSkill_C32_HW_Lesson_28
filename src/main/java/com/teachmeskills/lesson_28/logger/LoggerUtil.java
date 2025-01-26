package com.teachmeskills.lesson_28.logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtil {
    private final static String PATH_TO_LOG_FILE = "D:\\TeachMeSkills_C32_HW\\TeachMeSkill_C32_HW_Lesson_28\\src\\main\\resources\\logs\\log.txt";

    public static void logToFile(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logMessage = timestamp + " - " + message + System.lineSeparator();
        try {
            Files.write(Paths.get(PATH_TO_LOG_FILE), logMessage.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Could not write log file: " + PATH_TO_LOG_FILE);
        }
    }
}
